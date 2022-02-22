import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';

export default function NoAuthorization() {

    let navigate = useNavigate();

    const volver = () => {
        navigate("/");
    }

    return(
        <>
            <div className="container error-page">
                <h1>No tienes autorización para acceder &nbsp;&nbsp;</h1>
                <p>Esta ventana o componente solo puede ser ser accedido por un administrador</p>
                <button onClick={volver}>Volver al inicio</button><br/>
            </div>
        </>
    );

}