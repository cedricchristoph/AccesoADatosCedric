import axios from "axios";
import React, { useEffect, useState } from "react";
import IMesa from "../../model/entity/IMesa";
import ApiUtil from "../../model/util/ApiUtil";
import Mesas from "../authorized_user/Mesas";

interface IState {mesas: Array<IMesa> | null, error: string | null}
export default function VerDisponibilidad() {

    const[state, setState] = useState<IState>({mesas: null, error: null});


    function getMesasDisponibles(event: React.MouseEvent<HTMLFormElement>) {
        event.preventDefault();
        console.log("Entrado");
        let form = event.currentTarget as HTMLFormElement;
        let time = (form.time as HTMLInputElement).value.toString();
        let ocupantes: number = Number.parseInt((form.ocupantes as HTMLInputElement).value.toString());
        let millis = new Date(time).getTime();
        console.log(ocupantes);
        if (isNaN(ocupantes)) {
            setState({mesas: null, error: "Por favor introduzca una cantidad de ocupantes"});
            return;
        }
        const asyncConsulta = async () =>{
            try {
                let {data} = await axios.get(ApiUtil.getApiUrl() + "v1/mesas?time=" + millis + "&ocupantes=" + ocupantes);
                let mesas: Array<IMesa> = data;
                setState({mesas: mesas, error: null}); 
            } catch {
                setState({mesas: null, error: "No se puede buscar disponibilidad en el pasado"});
            }
        }
        asyncConsulta();
    }

    function reset() {
        setState({mesas: null, error: null});
    }

    return (
        <>
        <div className="container">
            <h1>Comprobar disponibilidad de mesas</h1>
            <p>
                Puede comprobar la disponibilidad en nuestro restaurante para una fecha y una hora concreta.
                Rellene el formulario siguiente:
            </p>
            {state.error != null ? 
            <>
                <p className="error-msg">{state.error}</p>
            </>
            :
            <></>
            }
            {state.mesas == null ? 
                <>
                    <form onSubmit={getMesasDisponibles}>
                        <input id="time" type="datetime-local" step="0.01"/><br/>
                        <input type="text" id="ocupantes" placeholder="Ocupantes"/><br/>
                        <input type="submit" className="submit-button" value="Consultar"/>
                    </form>
                </>
            :
                <>
                    {state.mesas.length === 0 ? 
                    <>
                        <p className="error-msg">
                            Para la fecha y hora indicada no se encontraron mesas o estas no disponen de la ocupación necesaria. 
                            Si son muchas personas recomandamos que nos llame para asegurarse de la disponibilidad.
                        </p>
                    </> 
                    : 
                    <>
                        <p>Se encontraron mesas disponibles. Contáctanos via teléfono o email para realizar una reserva</p>
                    </>}
                    <button onClick={reset} className="submit-button">Consultar para otra fecha</button>
                </>    
            }

        </div>
        </>
    );

}