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
                <h1>Ups. Eso no debería haber pasado ;( &nbsp;&nbsp;</h1>
                <p>Ocurrió un problema inesperado. Inténtelo de nuevo o contacte con un administrador si sigue ocurriendo.</p>
                <button onClick={volver}>Volver al inicio</button><br/>

                <img className="no_internet" width="300px"/>

            </div>
        </>
    );

}