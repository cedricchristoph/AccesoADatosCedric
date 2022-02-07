
import axios from "axios";
import { useEffect, useState } from "react";
import { isClassStaticBlockDeclaration } from "typescript";
import AlumnoCard from "../components/AlumnoCard";
import IAlumno from "../model/entity/IAlumno";
import ApiUrl from "../model/networking/ApiUtil";
import ApiUtil from "../model/networking/ApiUtil";

export default function Alumnos() {

    const [stalumnos, setStAlumnos] = useState<Array<IAlumno>>([]);
    
    useEffect(
        () => {
            selectAllAlumnos();    
        }, 
        []
    );

    async function selectAllAlumnos() {
        let {data} = await axios.get(ApiUrl() + "/alumnos");
        let arrAlumno: Array<IAlumno> = data;
        setStAlumnos(arrAlumno);
    }

    return (
        <>
        <div className="container">
            <h1>Alumnos</h1>
            <div className="alumno-wrapper">
                {stalumnos.map((alumno) => <AlumnoCard alumno={alumno}/>)}
            </div>
        </div>
        </>
    );
}
