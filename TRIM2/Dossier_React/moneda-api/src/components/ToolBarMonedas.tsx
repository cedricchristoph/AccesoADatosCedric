import axios from 'axios';
import React, { MouseEventHandler, useEffect, useState } from 'react';
import { useParams, Navigate, Route, Link, useNavigate } from "react-router-dom";
import IMoneda from '../model/entity/interface/IMoneda';
import { Moneda } from '../model/entity/Moneda';

export default function ToolBarMonedas() {
    
    let navigate = useNavigate();

    const addClicked = () => {
        navigate("/monedas/add");
    }

    return(
        <>
        <div className='bottom-tool-bar'>
            <button className='tool-bar-button' onClick={addClicked}>Añadir</button>
        </div>
        </>
    );

}