import axios from "axios";
import React from "react";
import { Navigate, useNavigate } from "react-router-dom";
import IMesa from "../../model/entity/IMesa";
import ApiUtil from "../../model/util/ApiUtil";

export default function AddMesa() {

    let navigate = useNavigate();

    function addMesa (event: React.MouseEvent<HTMLFormElement>){
        event.preventDefault();
        let form = event.currentTarget as HTMLFormElement;
        let nummesa: Number = Number.parseInt((form.nummesa as HTMLInputElement).value.toString());
        let ocupantes: Number = Number.parseInt((form.ocupantes as HTMLInputElement).value.toString());
        if (nummesa == null || ocupantes == null) {
            navigate("/connection_error");
            return;
        }

        let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };

        const asyncSave = async () => {
            let mesa: IMesa = {nummesa: nummesa, ocupantesmax: ocupantes, servicios: []}
            try {
                await axios.post(ApiUtil.getApiUrl() + "v3/mesas", mesa, headers);
            } catch {
                navigate("/connection_error");
                return;
            }
            navigate("/mesas");
        }
        asyncSave();
    }

    return (
        <>
        <div className="container">
            <h1>Añadir una mesa</h1>
            <form onSubmit={addMesa}>
                <input type="text" id="nummesa" placeholder="Numero de mesa"/><br/>
                <input type="text" id="ocupantes" placeholder="Ocupantes maximos"/><br/>
                <input type="submit" className="submit-button" value="Añadir"/><br/>
            </form>
        </div>
        </>
    );

}