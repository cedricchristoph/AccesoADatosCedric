import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import IMatricula from "../../model/entity/IMatricula";
import ApiUrl from "../../model/util/ApiUtil";
import EliminarMatricula from './EliminarMatricula';

interface IProps {dni: string | undefined}

export default function Matriculas(props: IProps) {

    const [stmatriculas, setStMatriculas] = useState<Array<IMatricula>>();
    let navigate = useNavigate();

    useEffect(() => {
        const selectMatriculas = async (id: string | undefined) => {
            let ruta = ApiUrl() + "/alumnos/" + id + "/matriculas";
            let {data} = await axios.get(ruta);
            let arrMatricula: Array<IMatricula> = data;
            console.log(arrMatricula);
            setStMatriculas(arrMatricula);
        }
        selectMatriculas(props.dni);
    }, []);

    const crearMatricula = () => {
        navigate("/alumnos/" + props.dni + "/matriculas/add" );
    }

    function eliminarMatricula (event: React.MouseEvent<HTMLButtonElement>) {
        let boton = event.currentTarget as HTMLButtonElement;
        event.preventDefault();
        let id = boton.name as string;
        navigate("/alumnos/" + props.dni + "/matriculas/" + id + "/delete");
    }

    return (
        <>
        <h2>Matrículas</h2>
        <button id="green-btn" onClick={crearMatricula}>Crear matrícula</button><br/>
            { stmatriculas?.length == null ? <span className="loader"></span> :   
                stmatriculas.length === 0 ? <p>No hay matriculas</p> : 
                <>
                    <table>
                        <th>Año de matriculación</th>
                        <th>Matrículas matriculadas</th>
                        <th>Herramientas</th>
                        {stmatriculas.map((matricula) => 
                        <tr className="clickable">
                            <td>{matricula.year}</td>
                            <td>{matricula.asignaturas}</td>
                            <td>
                                <button>Editar</button>&nbsp;
                                <button id="red-btn" name={""+matricula.idmatricula} onClick={eliminarMatricula}>Eliminar</button>
                            </td>
                        </tr>)
                        }   
                    </table>             
                </>
                
            }
        </>
    );

}