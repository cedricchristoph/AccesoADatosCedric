import { useState, useEffect } from 'react';
import IServicio from '../../model/entity/IServicio';
import IMesa from '../../model/entity/IMesa';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import ApiUtil from '../../model/util/ApiUtil';

interface IState {mesa: IMesa, servicioActual: IServicio | null}

export default function MesaDetails () {

    const [state, setState] = useState<IState>();
    let {mesaid} = useParams();
    let navigate = useNavigate();

    useEffect(() => {
        const asyncLoadMesa = async () => {
            try {
                let url = "v2/mesas/" + mesaid;
                let respuestaMesa = await axios.get(ApiUtil.getApiUrl() + url);
                let respuestaServicios = await axios.get(ApiUtil.getApiUrl() + url + "/servicios");
                let servicios:Array<IServicio> = respuestaServicios.data;
                let mesa: IMesa = respuestaMesa.data;
                mesa.servicios = servicios;
                let servicio: IServicio | null = null;
                try {
                    let respuestaServicio = await axios.get(ApiUtil.getApiUrl() + url + "/servicio_actual");
                servicio = respuestaServicio.data;
            } catch {}
                setState({mesa: mesa, servicioActual: servicio});
            } catch {
                navigate("/connection_error");
            }            
        }
        asyncLoadMesa();
    }, []);

    return (
        <>
        <div className='container'>
            <h1>Mesa {state?.mesa.nummesa}</h1>
            <p>Ocupación máxima: {state?.mesa.ocupantesmax} personas</p>
            {state?.servicioActual != null ?
            <><p>Mesa está en uso actualmente</p><button> Abrir servicio actual </button></>  
            : <button className='submit-button'>Iniciar nuevo servicio</button>}
            <h3>Servicio anteriores</h3>
            <table>
                <thead>
                    <th>Fecha entrada</th>
                    <th>Fecha salida</th>
                    <th></th>
                </thead>
                <tbody>
                {state?.mesa.servicios?.map((s) => 
                <tr>
                    <td>{s.fechacomienzo}</td>
                    <td>{s.fechafin}</td>

                </tr>)}
                </tbody>
            </table>
        </div>
        </>
    );

}