import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import IPlato from "../../model/entity/IPlato";
import ApiUtil from "../../model/util/ApiUtil";

export default function PlatosAdmin() {
    const [stPlatos, setStPlatos] = useState<Array<IPlato>>();

    let navigate = useNavigate();
    let refresh: Boolean = true;

    useEffect(() => {
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        const loadPlatos = async () => {
            // Recibir platos
            try {
                let { data } = await axios.get(
                    ApiUtil.getApiUrl() + "v2/platos",
                    headers
                );
                let platos: Array<IPlato> = data;
                setStPlatos(platos);
            } catch (error) {
                navigate("/connection_error");
            }
        };
        loadPlatos();
    }, []);


    function selectPlato (event: React.MouseEvent<HTMLButtonElement>) {
        let boton = event.currentTarget as HTMLButtonElement;
        let idplato: Number = Number.parseInt(boton.id);
        navigate("/platos/editar/" + idplato);
    }

    function disponible(event: React.MouseEvent<HTMLButtonElement>) {
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        event.preventDefault();
        let boton = event.currentTarget as HTMLButtonElement;
        let id: Number = Number.parseInt(boton.id.toString());
        const asyncDisponible = async () => {
            if (stPlatos == null) return;
            let newState: Array<IPlato> = [];
            for (let index = 0; index < stPlatos.length; index++) {
                const element: IPlato = stPlatos[index];
                if (element.idplato === id) {
                    element.disponible = 1;
                    await axios.put(ApiUtil.getApiUrl() + "v2/platos/" + id, element, headers);
                }
                newState.push(element);
            }
            setStPlatos(newState);
        }
        asyncDisponible();
    }

    function agotado(event: React.MouseEvent<HTMLButtonElement>) {
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        event.preventDefault();
        let boton = event.currentTarget as HTMLButtonElement;
        let id: Number = Number.parseInt(boton.id.toString());
        const asyncAgotado = async () => {
            if (stPlatos == null) return;
            let newState: Array<IPlato> = [];
            for (let index = 0; index < stPlatos.length; index++) {
                const element: IPlato = stPlatos[index];
                if (element.idplato === id) {
                    element.disponible = 0;
                    await axios.put(ApiUtil.getApiUrl() + "v2/platos/" + id, element, headers);                    
                }
                newState.push(element);
            }
            setStPlatos(newState);
        }
        asyncAgotado();
    }

    function addPlato() {
        navigate("/platos/add");
    }

    return (
        <>
            <div className="container">
                <h1>Administración de platos</h1>
                <button className="submit-button" onClick={addPlato}>Añadir nuevo plato</button>
                <br /><br/>
                {stPlatos?.map((p) => (
                    <>
                        <div className="plato-card">
                            <h3>
                                {p.nombre} <b>{p.preciounidad} €</b>
                            </h3>
                            <button id={p.idplato + ""} onClick={selectPlato}>
                                        Editar
                                    </button>
                            {p.disponible == 0 ? (
                                <button id={p.idplato + ""} className="red-green-button" onClick={disponible}>DISPONIBILIDAD</button>
                            ) : (
                                <button id={p.idplato + ""} className="green-red-button" onClick={agotado}>DISPONIBILIDAD</button>
                            )}
                        </div><br/>
                    </>
                ))}
            </div>
        </>
    );
}
