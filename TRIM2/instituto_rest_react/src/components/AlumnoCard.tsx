import { useState } from "react";
import { useNavigate } from "react-router-dom";
import IAlumno from "../model/entity/IAlumno";
import StringUtils from "../model/util/StringUtils";

interface IProps {alumno: IAlumno}
interface IState {alumno: IAlumno}

export default function AlumnoCard(props: IProps) {

    const [stalumno, setStAlumno] = useState<IState>({alumno: props.alumno});
    let navigate = useNavigate();

    const alumnoClicked = () => {
        let route = "/alumnos/" + stalumno.alumno.dni;
        navigate(route);
    }

    return (
        <>
        <div className="alumno-card" onClick={alumnoClicked}>
            <h3>{stalumno.alumno.nombre}&nbsp;{stalumno.alumno.apellidos}</h3>
            <p>
                DNI: {stalumno.alumno.dni} <br/>
                Fecha de Nacimiento: {StringUtils.getFormattedDate(stalumno.alumno.fechanacimiento)}
            </p>
        </div>
        </>
    );

}