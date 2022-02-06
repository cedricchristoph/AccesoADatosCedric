import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import IMoneda from '../model/entity/interface/IMoneda';
import { Moneda } from '../model/entity/Moneda';

interface IProps { moneda: IMoneda }
interface IState { moneda: IMoneda }

export default function MonedaCard(props: IProps) {
    
    const [stmoneda, setStmoneda] = useState<IState>({moneda: props.moneda});
    let navigate = useNavigate();
    
    const monedaClicked = () => {
        console.log("Clicked");
        let route = "/monedas/" + stmoneda.moneda.idmoneda;
        console.log(route);
        navigate(route);
    }

    return (
        <>
        <div className='moneda-card' onClick={monedaClicked}>
            <h3>{stmoneda.moneda?.nombre.toUpperCase()}</h3>
            <p>
                {stmoneda.moneda?.pais}
            </p>
        </div>
        </>
    );

}

