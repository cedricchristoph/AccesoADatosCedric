import axios from 'axios';
import React, { useState, useEffect } from 'react';
import IMesa from '../../model/entity/IMesa';
import ApiUtil from '../../model/util/ApiUtil';
import { useNavigate } from 'react-router-dom';
import MesaCard from '../../components/MesaCard';
import MesaDetails from './MesaDetails';

export default function Mesas () {

    const [state, setState] = useState<Array<IMesa>>();;
    let navigate = useNavigate();

    useEffect(() => {
        const asyncLoadMesas = async () => {
            let token:string = localStorage.getItem("token") as string;
            const headers = {
                headers: { Authorization: token }
            }
            try {
                let respuesta = await axios.get(ApiUtil.getApiUrl() + "v2/mesas", headers);
                let lista: Array<IMesa> = respuesta.data;
                setState(prevState => lista);
            } catch (error) {
                console.log(error);
                navigate("/connection_error");
            }
        }
        asyncLoadMesas();
    }, []);

    function addMesa() {
        navigate("/mesas/add");
    }

    return (
        <>
        <div className='container'>
            <h1>Mesas</h1>
            <button onClick={addMesa}>Añadir una mesa</button><br/><br/>
            {state?.length === 0 || state == null ? <span className='loader'/> : state?.map((m) => <MesaCard mesa={m} />)}
            
        </div>
        </>
    );

}