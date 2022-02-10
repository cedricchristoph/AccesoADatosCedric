import { useParams, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import axios from 'axios';
import ApiUrl from '../../model/util/ApiUtil';

export default function EliminarAsignatura() {

    let {id} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const eliminar = async () => {
            try {
                await axios.delete(ApiUrl() + "/asignaturas/" + id);
            } catch {
                console.log("Error");
            } finally {
                navigate("/asignaturas");
            }
        }
        eliminar();
    }, []);

    return (<></>);
}