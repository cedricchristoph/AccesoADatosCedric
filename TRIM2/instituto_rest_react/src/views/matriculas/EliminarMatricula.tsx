import { useParams, useNavigate } from 'react-router-dom';
import IAlumno from "../../model/entity/IAlumno";
import { useEffect } from 'react';
import axios from 'axios';
import ApiUrl from '../../model/util/ApiUtil';

export default function EliminarMatricula() {

    let {dni} = useParams();
    let {id} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const eliminar = async () => {
            try {
                await axios.delete(ApiUrl() + "/alumnos/" + dni + "/matriculas/" + id);
            } catch {
                console.log("Error");
            } finally {
                navigate("/alumnos/" + dni);
            }
        }
        eliminar();
    }, []);

    return (<></>);
}