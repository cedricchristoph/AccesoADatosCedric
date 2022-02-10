import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import IAlumno from '../../model/entity/IAlumno';
import ApiUrl from '../../model/util/ApiUtil';
import StringUtils from '../../model/util/StringUtils';

interface IProps {}
interface IState {alumno: IAlumno}

export default function EditAlumno(props: IProps) {
    
    const [stalumno, setStAlumno] = useState<IState>();
    let {dni} = useParams();
    let navigate = useNavigate();
    
    useEffect(() => {
        const selectAlumno = async (id: string | undefined) => {
            let ruta = ApiUrl() + "/alumnos/" + id;
            let {data} = await axios.get(ruta);
            let alumno: IAlumno = data;
            setStAlumno({alumno: alumno});
        }
        selectAlumno(dni);
    }, []);

    function save (event:React.FormEvent<HTMLFormElement>) {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let inputdni: HTMLInputElement = formulario.dni;
        let inputnombre: HTMLInputElement = formulario.nombre;
        let inputapellidos: HTMLInputElement = formulario.apellidos;
        let inputfecha: HTMLInputElement = formulario.fecha;
        let alumno: IAlumno = {
            dni: inputdni.value.toString(),
            nombre: inputnombre.value.toString(),
            apellidos: inputapellidos.value.toString(),
            fechanacimiento: new Date(inputfecha.value.toString()).getTime()
        };
        if (alumno.dni === "")
            alumno.dni = stalumno?.alumno.dni + "";
        if (alumno.nombre === "")
            alumno.nombre = stalumno?.alumno.nombre + "";
        if (alumno.apellidos === "")
            alumno.apellidos = stalumno?.alumno.apellidos + "";
        const asyncupdate = async () => {
            try {
                let {data} = await axios.put(ApiUrl() + "/alumnos/" + alumno.dni);
            } catch {
                console.log("Update error");
            }
        }
        asyncupdate();
        navigate("/");
    }

    return (
        <>
        <div className='container'>
            <h1>Registrar un nuevo alumno</h1>
            <form onSubmit={save}>
                <label>DNI</label><br/>
                <input type="text" id="dni" placeholder={stalumno?.alumno.dni + ""}/><br/>
                <label>Nombre</label><br/>
                <input type="text" id="nombre" placeholder={stalumno?.alumno.nombre + ""} /><br/>
                <label>Apellidos</label><br/>
                <input type="text" id="apellidos" placeholder={stalumno?.alumno.apellidos + ""} /><br/>
                <label>Fecha de Nacimiento</label><br/>
                <input type="date" id="fecha" placeholder='Introducir nueva fecha de nacimiento'/><br/>
                <input className="submit-button" type="submit" value="Guardar"/>
            </form>
        </div>
        </>
    );

}