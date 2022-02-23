import axios from "axios";
import React, { useEffect, useState } from "react";
import { Navigate, useNavigate, useNavigationType, useParams } from "react-router-dom";
import IPlato from "../../model/entity/IPlato";
import ApiUtil from "../../model/util/ApiUtil";

interface IState {plato: IPlato | null}
export default function EditarPlato () {
    
    const [state, setState] = useState<IState>();
    let {platoid} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const loadPlato = async () => {
            let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };
            
            let {data} = await axios.get(ApiUtil.getApiUrl() + "v2/platos/" + platoid, headers);
            let plato: IPlato = data;
            setState({plato: plato});
        }   
        loadPlato();
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
            let plato: IPlato | undefined | null = state?.plato;
            if (plato == null) {
                navigate("/connection_error");
                return;
            }
            if (nombre != null && nombre.length > 0)
                plato.nombre = nombre;
            if (descripcion != null && descripcion.length > 0)
                plato.descripcion = descripcion;
            if (precio != null)
                plato.preciounidad = precio;
            try {
                await axios.put(ApiUtil.getApiUrl() + "v3/platos/" + platoid, plato, headers);
            } catch {
                navigate("/connection_error");
            }
            navigate("/platos");
        }
        asyncSave();
    }

    function eliminarPlato() {
        const asyncDelete = async () => {
            let token: string = localStorage.getItem("token") as string;
                const headers = {
                    headers: { Authorization: token },
                };
            try {
                await axios.delete(ApiUtil.getApiUrl() + "v3/platos/" + platoid, headers);
            } catch {
                navigate("/connection_error");
            }
            navigate("/platos");
        }
        asyncDelete();
    }

    return (
    <>
    <div className="container">
        <h1>Editar plato</h1>
        {state?.plato != null ?
        <>
        <form onSubmit={save}>
            <input type="text" id="nombre" placeholder={state?.plato?.nombre + ""}/><br/>
            <input type="text" id="descripcion" placeholder={state?.plato?.descripcion + ""}/><br/>
            <input type="text" id="preciounidad" placeholder={state?.plato?.preciounidad + ""}/> €<br/>
            <input type="submit" className="submit-button" value="Guardar"/>
        </form>
        <br/>
        <button className="red-button" onClick={eliminarPlato}>Eliminar plato</button>
        </>
        :
        <span className="loader"/>
        }
    </div>
    </>
    );

}