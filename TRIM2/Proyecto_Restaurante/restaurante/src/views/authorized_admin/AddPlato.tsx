import axios from "axios";
import React, { useEffect, useState } from "react";
import { Navigate, useNavigate, useNavigationType, useParams } from "react-router-dom";
import IPlato from "../../model/entity/IPlato";
import ApiUtil from "../../model/util/ApiUtil";

export default function AddPlato () {
    
    let {platoid} = useParams();
    let navigate = useNavigate();

    useEffect(() => {

    }, [])

    function save (event: React.MouseEvent<HTMLFormElement>) {
        event.preventDefault();
        let form = event.currentTarget as HTMLFormElement;
        let nombre: String = (form.nombre as HTMLInputElement).value.toString();
        let descripcion: String = (form.descripcion as HTMLInputElement).value.toString();
        let precio: Number = Number.parseFloat((form.preciounidad as HTMLInputElement).value.toString());
        console.log(precio);
        const asyncSave = async () => {
            let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };
            let plato: IPlato = {idplato: 0, nombre: nombre, descripcion: descripcion, disponible: 0, preciounidad: precio};
            if (plato == null) {
                navigate("/connection_error");
                return;
            }

            try {
                await axios.post(ApiUtil.getApiUrl() + "v3/platos", plato, headers);
            } catch {
                navigate("/connection_error");
            }
            navigate("/platos");
        }
        asyncSave();
    }

    return (
    <>
    <div className="container">
        <h1>Editar plato</h1>
        <form onSubmit={save}>
            <input type="text" id="nombre" placeholder="Nombre"/><br/>
            <input type="text" id="descripcion" placeholder="Descripcion"/><br/>
            <input type="text" id="preciounidad" placeholder="Precio por unidad"/> €<br/>
            <input type="submit" className="submit-button" value="Añadir"/>
        </form>
    </div>
    </>
    );

}