import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import ApiUrl from '../../model/util/ApiUtil';
import IAsignatura from '../../model/entity/IAsignatura';

export default function AddAsignatura() {

    let navigate = useNavigate();

    function save(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let inputcurso: HTMLInputElement = formulario.curso;
        let inputnombre: HTMLInputElement = formulario.nombre;

        let asignatura: IAsignatura = {
            idasignatura: undefined,
            nombre: inputnombre.value,
            curso: inputcurso.value
        }

        const asyncsave = async () => {
            try {
                let { data } = await axios.post(ApiUrl() + "/asignaturas", asignatura);
                navigate("/asignaturas");
            } catch {
                console.log("Error");
            }
        }
        asyncsave();
    }

    return (
        <>
            <div className='container'>
                <h1>Añadir una nueva asignatura</h1>
                <form onSubmit={save}>
                    <label>Nombre de la asignatura</label><br />
                    <input type="text" id="nombre" placeholder='Introducir nombre de la asignatura' /><br /><br />
                    <label>Curso</label><br />
                    <input type="text" id="curso" placeholder='Introducir curso' /><br /><br />
                    
                    <input className="submit-button" type="submit" value="Guardar" />
                </form>
            </div>
        </>
    );

}