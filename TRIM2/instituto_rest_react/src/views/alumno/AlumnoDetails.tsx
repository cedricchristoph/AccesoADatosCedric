import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import AlumnoDetailToolBar from "../../components/alumno/AlumnoDetailToolBar";
import IAlumno from "../../model/entity/IAlumno";
import ApiUrl from "../../model/util/ApiUtil";
import StringUtils from "../../model/util/StringUtils";
import Matriculas from "../matriculas/Matriculas";

interface IState {alumno?: IAlumno}

export default function AlumnoDetails() {

    const [stalumno, setStAlumno] = useState<IState>({alumno: {dni: "", nombre: "", apellidos: "", fechanacimiento: 0}});
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
            { stalumno.alumno == null ? <span className="loader"></span> : 
                <>
                <h1>{stalumno.alumno?.nombre}&nbsp;{stalumno.alumno?.apellidos}</h1>
                <p>
                    DNI: {stalumno.alumno?.dni} <br/>
                    Fecha de Nacimiento: {StringUtils.getFormattedDate(stalumno.alumno?.fechanacimiento)}
                </p>
                <br/>
                <Matriculas dni={id}/>
                </>
            }
        </div>
        {stalumno.alumno != null ? <AlumnoDetailToolBar alumno={stalumno.alumno}/> : <></>}
        </>
    );

}