import { useEffect, useState } from 'react';
import IPlato from '../../model/entity/IPlato';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import ApiUtil from '../../model/util/ApiUtil';

interface IProps {setParentPlato: Function}
export default function ElegirPlato(props: IProps) {

    const [stPlatos, setStPlatos] = useState<Array<IPlato>>();
    let navigate = useNavigate();

    useEffect(() => {
        let token: string = localStorage.getItem("token") as string;
        const headers = {
            headers: { Authorization: token },
        };
        const loadPlatos = async () => {
            // Recibir platos
            try {
                let { data } = await axios.get(ApiUtil.getApiUrl() + "v2/platos", headers)
                let platos: Array<IPlato> = data;
                setStPlatos(platos);
            } catch (error) {
                navigate("/connection_error");
            }
        }
        loadPlatos();
    }, []);

    function selectPlato (event: React.MouseEvent<HTMLButtonElement>) {
        let boton = event.currentTarget as HTMLButtonElement;
        let idplato: Number = Number.parseInt(boton.id);
        props.setParentPlato(idplato);
    }

    return (
        <>
        {stPlatos != null ? stPlatos.map((p) => 
        <div className='plato-card'>
            <h3>{p.nombre} <b>{p.preciounidad} €</b></h3>
            {p.disponible === 0 ? <h4 className='error-msg'>AGOTADO</h4> : 
            <>
            <button id={p.idplato + ""} onClick={selectPlato}>Seleccionar</button>
            </>
            }
        </div>)
        :
        <span className='loader'/>
        }
        </>
    );

}