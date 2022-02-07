import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import IMoneda from '../model/entity/interface/IMoneda';
import { Moneda } from '../model/entity/Moneda';

interface IState {moneda: IMoneda}

export default function AddMoneda() {
    
    const [stmoneda, setStmoneda] = useState<IState>({moneda: {idmoneda: null, nombre: "", pais: "", historicos: []}});
    let navigate = useNavigate();
    
    function save (event:React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let inputnombre: HTMLInputElement = formulario.nombre;
        let inputpais: HTMLInputElement = formulario.country;
        let moneda: IMoneda = {
            idmoneda: null, 
            nombre: inputnombre.value.toString(), 
            pais: inputpais.value.toString(), 
            historicos: []
        };
        const asyncsave = async () => {
            let rutaDeMoneda = "http://localhost:8080/api/v1/monedas";
            
            try {
                let { data } = await axios.post(rutaDeMoneda, moneda);
            } catch {
                console.log()
            } 
        }
        asyncsave();
        navigate("/");
    }

    return (
        <>
        <div className='container'>
            <h1>Añadir una moneda</h1>
            <form onSubmit={save}>
                <label>Nombre</label><br/>
                <input type="text" id="nombre"/><br/>
                <label>País</label><br/>
                <input type="text" id="country"/><br/>
                <input className="submit-button" type="submit" value="Guardar"/>
            </form>
        </div>
        </>
    );

}

