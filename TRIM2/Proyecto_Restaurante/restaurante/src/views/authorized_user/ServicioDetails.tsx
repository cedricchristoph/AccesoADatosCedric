
import axios from "axios";
import { stat } from "fs";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import IServicio from "../../model/entity/IServicio";
import ApiUtil from "../../model/util/ApiUtil";
import StringUtils from "../../model/util/StringUtils";

interface IState {servicio: IServicio | undefined, total: Number | undefined, addProduct: Boolean}
export default function ServicioDetails () {

    const [state, setState] = useState<IState>({servicio: undefined, total: undefined, addProduct:false});

    let {mesaid} = useParams();
    let {servicioid} = useParams();

    useEffect(() => {
        const asyncLoadServicio = async () => {
            let url = ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid; 
            let {data} = await axios.get(url);
            let servicio: IServicio = data;
            let totalResponse = await axios.get(url + "/total");
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

    return (
        <>
        <div className="container">
            <h1>Servicio <b>#{state?.servicio?.idservicio}</b></h1>
            <p>Servicio comenzó {state?.servicio?.fechacomienzo}</p>
            {state?.servicio?.fechafin?<p>Servicio finalizó: {state?.servicio.fechafin}</p>:<></>}
            {state?.servicio?.reservada?<p>Reservada a {state?.servicio.reservada}</p>:<></>}
            {state?.servicio?.pagada === 1 ? <p className="success-msg"><b>Factura pagada</b></p>:<p className="error-msg"><b>Factura NO pagada</b></p>}
            <br/>
            <h3>Pedidos realizados</h3>
            
            <button className="submit-button">Realizar un nuevo pedido</button>
            
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
                        <button>X</button>
                        <button>Edit</button>
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
        </div>
        </>
    );

}