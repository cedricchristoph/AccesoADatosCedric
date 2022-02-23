import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import IMesa from "../../model/entity/IMesa";
import ApiUtil from "../../model/util/ApiUtil";

interface IState { mesa: IMesa }
export default function EditMesa() {

    const [state, setState] = useState<IState>();
    let { mesaid } = useParams();
    let navigate = useNavigate();

    useEffect(() => {

        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };

        const loadMesa = async () => {
            try {
                let { data } = await axios.get(ApiUtil.getApiUrl() + "v2/mesas/" + mesaid, headers);
                let mesa: IMesa = data;
                setState({ mesa: mesa });
            } catch {
                navigate("connection_error");
            }
        }
        loadMesa();
    }, [])

    function save(event: React.MouseEvent<HTMLFormElement>) {
        event.preventDefault();
        let form = event.currentTarget as HTMLFormElement;
        let ocupantes: Number = Number.parseInt((form.ocupantes as HTMLInputElement).value.toString());
        if (mesaid == null || state?.mesa == null) {
            navigate("/connection_error");
            return;
        }
        let mesa: IMesa = {nummesa: Number.parseInt(mesaid), ocupantesmax: state?.mesa.ocupantesmax, servicios: []}
 
        if (ocupantes != null)
            mesa.ocupantesmax = ocupantes;
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        const asyncSave = async () => {
            try {
                let { data } = await axios.put(ApiUtil.getApiUrl() + "v3/mesas/" + mesaid, mesa, headers);
                navigate("/mesas");
            } catch {
                navigate("/connection_error");
            }
        }
        asyncSave();
    }

    function eliminarMesa() {
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        const asyncDelete = async () => {
            try {
                await axios.delete(ApiUtil.getApiUrl() + "v3/mesas/" + mesaid, headers);
                navigate("/mesas");
            } catch {
                navigate("/connection_error");
            }
        }
        asyncDelete();
    }


    return (
        <>
            <div className="container">
                {state?.mesa != null ?
                <>
                    <h1>Editando mesa {state.mesa.nummesa}</h1>
                    <form onSubmit={save}>
                        <input type="text" id="ocupantes" placeholder="Ocupantes maximos" /><br />
                        <input type="submit" className="submit-button" value="Guardar" /><br />
                    </form> 
                    <br/>
                    <br/>
                    <button onClick={eliminarMesa} className="red-button">Eliminar mesa</button>
                </>: <span className="loader" />}
            </div>
        </>
    );

}