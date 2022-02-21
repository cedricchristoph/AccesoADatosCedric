import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';

export default function ApiConnectionError() {

    let navigate = useNavigate();

    const volver = () => {
        navigate("/");
    }

    return(
        <>
            <div className="container error-page">
                <h1>Error de conexión</h1>
                <p>Se interrumpió la conexión con el servidor. Compruebe su conexión a internet o reintente en unos minutos.</p>
                <button onClick={volver}>Reintentar</button><br/>

                <img className="no_internet" width="300px"/>

            </div>
        </>
    );

}