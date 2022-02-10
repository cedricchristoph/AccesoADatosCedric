import { useParams, useNavigate } from 'react-router-dom';
import IAlumno from "../../model/entity/IAlumno";
import { useEffect } from 'react';
import axios from 'axios';
import ApiUrl from '../../model/util/ApiUtil';

export default function EliminarAlumno() {

    let {dni} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const eliminar = async () => {
            await axios.delete(ApiUrl() + "/alumnos/" + dni);
            navigate("/");
        }
        eliminar();
    }, []);

    return (<></>);
}