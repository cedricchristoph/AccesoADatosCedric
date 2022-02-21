import axios from 'axios';
import { useState, useEffect } from 'react';
import IMesa from '../../model/entity/IMesa';
import ApiUtil from '../../model/util/ApiUtil';
import { useNavigate } from 'react-router-dom';
import MesaCard from '../../components/MesaCard';


export default function Mesas () {

    const [mesas, setMesas] = useState<Array<IMesa>>();
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
                setMesas(lista);
            } catch {
                navigate("/connection_error");
            }
        }
        asyncLoadMesas();
    }, []);

    return (
        <>
        <div className='container'>
            <h1>Mesas</h1>
            {mesas?.map((m) => <MesaCard mesa={m}/>)}
        </div>
        </>
    );

}