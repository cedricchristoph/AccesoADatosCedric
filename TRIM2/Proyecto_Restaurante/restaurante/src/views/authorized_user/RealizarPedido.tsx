import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import IDetalleFactura from "../../model/entity/IDetalleFactura";
import IMesa from "../../model/entity/IMesa";
import IPlato from "../../model/entity/IPlato";
import IServicio from "../../model/entity/IServicio";
import ApiUtil from "../../model/util/ApiUtil";

interface IState {
    mesa: IMesa | undefined, 
    servicio: IServicio | undefined, 
    platos: Array<IPlato> | undefined, 
    pedido: IDetalleFactura | undefined
}

export default function RealizarPedido () {

    const [state, setState] = useState<IState>({mesa: undefined, servicio: undefined, platos: undefined, pedido: {
        plato: {idplato: 0, descripcion: "", disponible: 0, nombre: "", preciounidad: 0},
        cantidad: 0,
        preciounidad: 0,
        iddetallefactura: 0            
    }});
    let {mesaid} = useParams();
    let {servicioid} = useParams();
    let navigate = useNavigate();


    useEffect(() => {

        // Cargar valores de api
        const loadApiValues = async () => {
            let token: string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token },
            };
            // Recibir objeto mesa
            let newState: IState | undefined = state;
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid, headers);
                let mesa: IMesa = data;
                newState.mesa = mesa;
                setState(newState);
            } catch (error) {
                navigateToErrorPage();
            }
            // Recibir objeto servicio
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid, headers);
                let servicio: IServicio = data;
                newState.servicio = data;
                setState(newState);
            } catch (error) {
                navigateToErrorPage();
            }
            // Recibir platos
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/platos", headers)
                let platos: Array<IPlato> = data;
                newState.platos = platos;
                setState(newState);
            } catch (error) {
                navigateToErrorPage();
            } 
        }
        loadApiValues();
    }, [])

    function navigateToErrorPage() {
        navigate("/connection_error");
    }

    function selectPlato(event: React.MouseEvent<HTMLButtonElement>){
        let boton = event.currentTarget as HTMLButtonElement;
        let platoSeleccionado: Number = Number.parseInt(boton.id);
        const loadPlato = async () => {
            let token: string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token },
            };
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/platos/" + platoSeleccionado, headers);
                let plato: IPlato = data;
                let newState = state;
                if (newState.pedido != null)
                    newState.pedido.plato = plato;
                setState(newState);
            } catch {
                navigateToErrorPage();
            }
        }
        loadPlato();
    }

    function crearPedido(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let cantidad: HTMLInputElement = formulario.cantidad;
        let pedido: IDetalleFactura;
        
        if (state.pedido != null) {
            pedido = state.pedido;
            pedido.cantidad =  Number.parseInt(cantidad.value.toString());
            if (pedido.cantidad <= 0)
                return;
        }

        const asyncCrearPedido = async () => {
            let token: string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token },
            };
            try {
                let url = ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid + "/detallesfactura";
                let respuesta = await axios.post(url, pedido, headers);
                navigate("/mesas/" + mesaid + "/servicios/" + servicioid);
            } catch (error) {
                navigateToErrorPage();
            }
        }
        asyncCrearPedido();
    }

    return (

        <>

        <div className="container">
            <h1>Añadir un nuevo pedido</h1>
            <h4>Agregando un pedido al servicio #{state.servicio?.idservicio} de la mesa {state.mesa?.nummesa}</h4>
            <br/>
            {state.platos != null ? state.platos.map((p) => 
                <div className="plato-card">
                    <h2>{p.nombre}</h2>
                    <h2>{p.preciounidad} €</h2>
                    {p.disponible === 0 ? <h2 className="error-msg">AGOTADO</h2> :<></>}
                    <button id={p.idplato + ""} onClick={selectPlato}>Seleccionar</button>
                </div>
            ) : <span className="loader"/>
            }
 
            <h3>Seleccionar cantidad del producto</h3>
            <form onSubmit={crearPedido}>
                <input type="text" id="cantidad" placeholder="Introducir cantidad"/>
                <input type="submit" className="submit-button" value="Realizar pedido"/>
            </form>

        </div>

        </>

    );

}