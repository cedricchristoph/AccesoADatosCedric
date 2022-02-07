import { useState } from "react";
import { useNavigate } from "react-router-dom";
import IAlumno from "../model/entity/IAlumno";

interface IProps {alumno: IAlumno}
interface IState {alumno: IAlumno}

export default function AlumnoCard(props: IProps) {

    const [stalumno, setStAlumno] = useState<IState>({alumno: props.alumno});
    let navigate = useNavigate();

    const alumnoClicked = () => {
        let route = "/alumnos/" + stalumno.alumno.id;
        navigate(route);
    }

    return (
        <>
        <div className="alumno-card" onClick={alumnoClicked}>
            <h3>{stalumno.alumno.nombre}&nbsp;{stalumno.alumno.apellidos}</h3>
            <p>
                DNI: {stalumno.alumno.id} <br/>
                Fecha de Nacimiento: {stalumno.alumno.fechanacimiento}
            </p>
        </div>
        </>
    );

}