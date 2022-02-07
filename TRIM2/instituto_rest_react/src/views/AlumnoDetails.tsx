import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import IAlumno from "../model/entity/IAlumno";
import ApiUrl from "../model/networking/ApiUtil";

interface IState {alumno?: IAlumno}

export default function AlumnoDetails() {

    const [stalumno, setStAlumno] = useState<IState>({});
    let {id} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const selectAlumno = async (id: string | undefined) => {
            let ruta = ApiUrl() + "/alumnos/" + id;
            let {data} = await axios.get(ruta);
            let alumno: IAlumno = data;
            setStAlumno({alumno: alumno});
        }
        selectAlumno(id);
    }, [id]);

    return (
        <>
        <div className="container">
            <h1>{stalumno.alumno?.nombre}&nbsp;{stalumno.alumno?.apellidos}</h1>
            <p>
                DNI: {stalumno.alumno?.id} <br/>
                Fecha de Nacimiento: {stalumno.alumno?.fechanacimiento}
            </p>
        </div>
        </>
    );

}