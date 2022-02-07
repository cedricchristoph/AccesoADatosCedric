import axios from 'axios';
import React, { useEffect, useState } from 'react';
import MonedaCard from '../components/MonedaCard';
import ToolBarMonedas from '../components/ToolBarMonedas';
import IMoneda from '../model/entity/interface/IMoneda';
import MonedaService from '../service/MonedaService';

export default function Monedas() {

    const [monedas, setMonedas] = useState<Array<IMoneda>>([]);

    useEffect(
        () => {selectAllMonedas()}, []
    );

    return (
        <>
        
        <div className='container'>
            <h1>Monedas</h1>
            <div className='moneda-wrapper'>
                {monedas.map((moneda) => <MonedaCard moneda={moneda}/>)}
            </div>
            <ToolBarMonedas />
        </div>
        </>
    );

    async function selectAllMonedas() {
        let rutaDeMoneda = "http://localhost:8080/api/v1/monedas";
        let { data } = await axios.get(rutaDeMoneda);
        let arrMonedas: Array<IMoneda> = data;
        console.log(arrMonedas);
        setMonedas(arrMonedas);
                /*MonedaService.selectById(idmoneda)
                .then((response: any) => {
                    let moneda: IMoneda = response;
                    setStmoneda({moneda});
                })
                .catch((e: Error) => {
                    console.log(e.message);
                });*/
    }
    

}