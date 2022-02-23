
import axios from "axios";
import { stat } from "fs";
import React, { useEffect, useState } from "react";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import { EventEmitter } from "stream";
import IServicio from "../../model/entity/IServicio";
import ApiUtil from "../../model/util/ApiUtil";
import StringUtils from "../../model/util/StringUtils";

interface IState {servicio: IServicio | undefined, total: Number | undefined, addProduct: Boolean}
export default function ServicioDetails () {

    const [state, setState] = useState<IState>({servicio: undefined, total: undefined, addProduct:false});

    let {mesaid} = useParams();
    let {servicioid} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        let token:string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token }
            }
        const asyncLoadServicio = async () => {
            let url = ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid; 
            let {data} = await axios.get(url, headers);
            let servicio: IServicio = data;
            let totalResponse = await axios.get(url + "/total", headers);
            let total: Number = totalResponse.data;
            console.log(servicio);
            console.log(total);
            setState({servicio: servicio, total: total, addProduct: false});
        }
        asyncLoadServicio();
    }, []);


    function pagarYTerminarServicio () {
        
        let servicio: IServicio = {
            idservicio: state?.servicio?.idservicio,
            fechacomienzo: state?.servicio?.fechacomienzo,
            fechafin: StringUtils.getFormattedDate(new Date().getTime()),
            pagada: 1,
            reservada: state?.servicio?.reservada,
            detallefacturas: state?.servicio?.detallefacturas
        }
        console.log(servicio);
        const asyncSave = async () => {
            let url = ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid; 
            let {data} = await axios.put(url, servicio);
            setState({servicio: servicio, total: state?.total, addProduct: false})
        }
        asyncSave();
        
    }

    function nuevoPedido() {
        navigate("/mesas/" + mesaid + "/servicios/" + servicioid + "/add");
    }

    function eliminarPedido(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        let boton = event.currentTarget as HTMLButtonElement;
        let id = boton.id
        let token:string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token }
            }
        const asyncDelete = async () => {
            await axios.delete(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid + "/detallesfactura/" + id);
            let newState: IState = {total: state.total, addProduct: state.addProduct, servicio: state.servicio}
            setState(newState);
        }
        asyncDelete();
    }

    
    function editarPedido(event: React.MouseEvent<HTMLButtonElement>) {
        event.preventDefault();
        let boton = event.currentTarget as HTMLButtonElement;
        let id = boton.id
        navigate("/mesas/" + mesaid + "/servicios/" + servicioid + "/pedidos/" + id);

    }

    return (
        <>
        <div className="container">

            {state?.servicio? 
            <>
            <h1>Servicio <b>#{state?.servicio?.idservicio}</b></h1>
            <p>Servicio comenzó {state?.servicio?.fechacomienzo}</p>
            {state?.servicio?.fechafin?<p>Servicio finalizó: {state?.servicio.fechafin}</p>:<></>}
            {state?.servicio?.reservada?<p>Reservada a {state?.servicio.reservada}</p>:<></>}
            {state?.servicio?.pagada === 1 ? <p className="success-msg"><b>Factura pagada</b></p>:<p className="error-msg"><b>Factura NO pagada</b></p>}
            <br/>
            <h3>Pedidos realizados</h3>
            
            <button className="submit-button" onClick={nuevoPedido}>Realizar un nuevo pedido</button>
            
            <table>
                <th>Producto pedido</th>
                <th>Cantidad</th>
                <th>Precio/ud</th>
                <th>Acciones</th>
                {state?.servicio?.detallefacturas?.map((d) => 
                <tr>
                    <td>{d.plato.nombre}</td>
                    <td>{d.cantidad}</td>
                    <td>{d.preciounidad} €</td>
                    <td>
                        <button id={d.iddetallefactura + ""} onClick={eliminarPedido}>X</button>
                        <button id={d.iddetallefactura + ""} onClick={editarPedido}>Editar</button>
                    </td>
                </tr>)}
            </table>
            {state?.servicio?.pagada === 0 ? 
                <>
                    <h3>TOTAL A PAGAR: {state?.total} €</h3>
                    <button className="submit-button" onClick={pagarYTerminarServicio}>Marcar pagado y finalizar servicio</button>
                </> : 
                <>
                    <h3>TOTAL COBRADO: {state?.total} €</h3>
                </>
                }
            </>
            
            : 
            
            <span className="loader"/> 
            
            }

            
        </div>
        </>
    );

}