import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import IDetalleFactura from "../../model/entity/IDetalleFactura";
import ApiUtil from "../../model/util/ApiUtil";

interface IState {pedido: IDetalleFactura}
export default function CambiarCantidadDePedido () {

    const [state, setState] = useState<IState>();
    let navigate = useNavigate();
    let {pedidoid} = useParams();
    let {servicioid} = useParams();
    let {mesaid} = useParams();

    useEffect(() => {
        const loadPedido = async () => {
            try {
                let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid + "/detallesfactura/" + pedidoid, headers);
                let pedido: IDetalleFactura = data;
                setState({pedido: pedido});
            } catch {
                navigate("/connection_error");
            }
        }
        loadPedido();
    });

    function save (event: React.MouseEvent<HTMLFormElement>) {
        event.preventDefault();
        let form = event.currentTarget as HTMLFormElement;
        let cantidad = Number.parseInt((form.cantidad as HTMLInputElement).value.toString());
        if (cantidad == null) {
            navigate("/connection_error");
            return;
        }
        let pedido: IDetalleFactura | undefined = state?.pedido;
        if (pedido == null) {
            navigate("/connection_error");
            return;
        }
        pedido.cantidad = cantidad;
        let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };
        const asyncSave = async () => {
            try {
                await axios.put(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid + "/servicios/" + servicioid + "/detallesfactura/" + pedidoid, pedido, headers);
                navigate("/mesas/" + mesaid + "/servicios/" + servicioid);
            } catch {
                navigate("/connection_error");
                return;
            }
        }
        asyncSave();
    }

    return (
        <>
        <div className="container">
            <h1>Editar cantidad de producto</h1>
            <p>Producto seleccionado: <b>{state?.pedido.plato.nombre}</b></p>
            <form onSubmit={save}>
                <input type="text" id="cantidad" placeholder="Introducir nueva cantidad"/>
                <input type="submit" className="submit-button"/>
            </form>
        </div>
        </>
    );

}