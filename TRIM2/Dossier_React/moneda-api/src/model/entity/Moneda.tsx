import IMoneda from "./interface/IMoneda";
import IHistorico from "./interface/IHistorico";
import React from 'react';
import { useParams } from "react-router-dom";
import { useEffect, useState } from 'react';
import axios from 'axios';
import MonedaService from "../../service/MonedaService";

interface IState { moneda?: IMoneda }

export function Moneda() {

    const [stmoneda, setStmoneda] = useState<IState>({});
    const { idmoneda } = useParams();

    useEffect(
        () => {
            const getMoneda = async (monedaid: string | undefined) => {
                let rutaDeMoneda = "http://localhost:8080/api/v1/monedas/";
                let { data } = await axios.get(rutaDeMoneda + monedaid);
                let moneda: IMoneda = data;
                console.log(moneda);
                setStmoneda({moneda: moneda});
            }
            getMoneda(idmoneda);
            /*MonedaService.selectById(idmoneda)
            .then((response: any) => {
                let moneda: IMoneda = response;
                setStmoneda({moneda});
            })
            .catch((e: Error) => {
                console.log(e.message);
            });*/
        },
        [idmoneda]
    );

    return (
        <>
            <div className="container">
                <h1>Moneda #{stmoneda.moneda?.idmoneda}</h1>
                <p>
                    <b>Nombre: </b>{stmoneda.moneda?.nombre.toUpperCase()}
                <br/>
                    <b>País: </b>{stmoneda.moneda?.pais}
                </p>
            </div>
        </>
    );

};
