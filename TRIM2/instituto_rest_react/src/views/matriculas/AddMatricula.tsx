import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import IMatricula from '../../model/entity/IMatricula';
import ApiUrl from '../../model/util/ApiUtil';
import IAsignatura from '../../model/entity/IAsignatura';

interface IProps {}
interface IState {asignaturas: Array<IAsignatura>}

export default function AddMatricula() {
    
    const [stasignaturas, setStAsignaturas] = useState<IState>();
    const [stasignatura, setStAsignatura] = useState<IAsignatura>();

    let {dni} = useParams();
    let navigate = useNavigate();
    
    useEffect(() => {
        async function selectAllAsignaturas() {
            let {data} = await axios.get(ApiUrl() + "/asignaturas");
            let arrAsignatura: Array<IAsignatura> = data;
            console.log(arrAsignatura);
            setStAsignaturas({asignaturas: arrAsignatura});
        }       
        selectAllAsignaturas();     
    }, []);

    function save (event:React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let inputyear: HTMLInputElement = formulario.year;
        let inputasignaturas: HTMLSelectElement = formulario.asignaturas;
        let asignaturas: Array<IAsignatura> = new Array<IAsignatura>();
        const getAsignatura = async (id: any) => {
            let {data} = await axios.get(ApiUrl() + "/asignaturas/" + id);
            asignaturas.push(data as IAsignatura);
        }
        getAsignatura(inputasignaturas.value).then(() => {
            const asyncsave = async () => {
                try {
                    let { data } = await axios.post(ApiUrl() + "/alumnos/" + dni + "/matriculas", matricula);
                    navigate("/alumnos/" + dni);
                } catch {
                    console.log("Error");
                } 
            }
            let matricula: IMatricula = {
                idmatricula: undefined,
                year: Number.parseInt(inputyear.value),
                asignaturas: asignaturas
            };
            asyncsave();
        });
    }

    return (
        <>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <script src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
        <div className='container'>
            <h1>Crear una nueva matrícula</h1>
            <p>Para alumno con dni {dni}</p>
            <form onSubmit={save}>
                <label>Año de matriculación</label><br/>
                <input type="text" id="year" placeholder='Introducir año'/><br/><br/>
                <label>Asignaturas</label><br/>
                <select data-placeholder="Introduzca nombre de asignatura" multiple className="chosen-select" name="asignaturas">
                    {stasignaturas?.asignaturas.map((asignatura) => 
                        <option value={asignatura.idasignatura + ""}>{asignatura.nombre + " " + asignatura.curso}</option>
                    )}
                </select><br/>
                <input className="submit-button" type="submit" value="Guardar"/>
            </form>
        </div>
        </>
    );

}