import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import IMoneda from '../model/entity/interface/IMoneda';
import { Moneda } from '../model/entity/Moneda';

interface IState {moneda: IMoneda}

export default function AddMoneda() {
    
    const [stmoneda, setStmoneda] = useState<IState>({moneda: {idmoneda: -1, nombre: "", pais: "", historicos: []}});
    let navigate = useNavigate();
    
    const save = async () => {
        let rutaDeMoneda = "http://localhost:8080/api/v1/monedas/";
        axios.post(rutaDeMoneda, stmoneda).then(() => navigate("/")).catch(() => {});
    }

    return (
        <>
        <div className='container'>
            <h1>Añadir una moneda</h1>
            <form onSubmit={save}>
                <label>ID:</label><br/>
                <input type="text" id="id" onChange={(e) => {
                    stmoneda.moneda.idmoneda = Number.parseInt(e.target.value);
                    setStmoneda(stmoneda);
                }}/><br/>
                <label>Nombre</label><br/>
                <input type="text" id="nombre" onChange={(e) => {
                    stmoneda.moneda.nombre = e.target.value;
                    setStmoneda(stmoneda);
                }}/><br/>
                <label>País</label><br/>
                <input type="text" id="country" onChange={(e) => {
                    stmoneda.moneda.pais = e.target.value;
                    setStmoneda(stmoneda);
                }}/><br/>
                <input className="submit-button" type="submit" value="Guardar"/>
            </form>
        </div>
        </>
    );

}

