import axios from 'axios';
import { useState, useEffect } from 'react';
import IPlato from '../../model/entity/IPlato';
import ApiUtil from '../../model/util/ApiUtil';
export default function VerCarta () {

    const [cartaState, setCartaState] = useState<Array<IPlato>>();

    useEffect(() => {
        const asyncLoadPlatos = async () => {
            let { data } = await axios.get(ApiUtil.getApiUrl() + "v1/platos");
            let platos: Array<IPlato> = data;
            setCartaState(platos);
        }
        asyncLoadPlatos();
    }, [])

    return (
        <>
        <div className='container'>
            <h1>Carta de nuestro restaurante</h1>
            {cartaState?.map((p) => 
                <>
                <div className='plato-card'>
                    <p><b>{p.nombre}</b></p>
                    <p>{p.descripcion}</p> 
                    {p.disponible === 0 ? <h3 className='error-msg'>Agotado</h3> : <h3>{p.preciounidad} €</h3>}
                </div> <br/>
                </>
            )}
            <p>Cualquier duda que tenga sobre nuestros platos, ¡llámenos!</p>
        </div>
        </>
    );

}