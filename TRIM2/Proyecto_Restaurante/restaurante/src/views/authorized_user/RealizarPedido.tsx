import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import IDetalleFactura from "../../model/entity/IDetalleFactura";
import IMesa from "../../model/entity/IMesa";
import IPlato from "../../model/entity/IPlato";
import IServicio from "../../model/entity/IServicio";
import ApiUtil from "../../model/util/ApiUtil";
import ElegirPlato from "./ElegirPlato";

interface IState {
    mesa: IMesa | undefined, 
    servicio: IServicio | undefined,  
    plato: IPlato | null,
    pedido: IDetalleFactura | undefined
}

export default function RealizarPedido () {

    const [state, setState] = useState<IState>({mesa: undefined, servicio: undefined, plato: null , pedido: {
        plato: {idplato: 0, descripcion: "", disponible: 0, nombre: "", preciounidad: 0},
        cantidad: 0,
        preciounidad: 0,
        iddetallefactura: 0            
    }});

    let refresh : any = "a";
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

            let mesa: IMesa = {nummesa: 0, ocupantesmax: 0, servicios: null};
            let servicio: IServicio = {
                idservicio: 0,
                detallefacturas: null,
                fechacomienzo: "",
                fechafin: "",
                pagada: 0,
                reservada: ""  
            };

            // Recibir objeto mesa
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid, headers);
                mesa = data;
                console.log(mesa);
                
            } catch (error) {
                navigateToErrorPage();
            }
            // Recibir objeto servicio
            try { 
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid, headers);
                servicio = data;
            } catch (error) {
                navigateToErrorPage();
            }

            setState({mesa: mesa, pedido: undefined, servicio: servicio, plato: null})
        }
        loadApiValues();
    }, []);

    function navigateToErrorPage() {
        navigate("/connection_error");
    }

    function selectPlato(idplato: Number){
        
        const loadPlato = async () => {
            let token: string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token },
            };
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/platos/" + idplato, headers);
                let plato: IPlato = data;
                console.log(plato);
                setState({mesa: state.mesa, pedido: state.pedido, servicio: state.servicio, plato: plato});
                console.log(state.plato);
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
        
        
        if (state.plato != null) {
            let pedido: IDetalleFactura = {
                plato: state.plato,
                cantidad: Number.parseInt(cantidad.value.toString()),
                preciounidad: 0,
                iddetallefactura: 0
            };
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
        } else navigateToErrorPage();

        
    }

    return (

        <>

        <div className="container">
            <h1>Añadir un nuevo pedido</h1>
            <h4>Agregando un pedido al servicio #{state.servicio?.idservicio} de la mesa {state.mesa?.nummesa}</h4>
            <br/>
            {state.plato == null ? 
            <>
                <ElegirPlato setParentPlato={selectPlato}/>
            </> 
            : 
            <>
            <h3>Seleccionar cantidad del producto</h3>
            <form onSubmit={crearPedido}>
                <input type="text" id="cantidad" placeholder="Introducir cantidad"/>
                <input type="submit" className="submit-button" value="Realizar pedido"/>
            </form>
            </>
            }

        </div>

        </>

    );

}