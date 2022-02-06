import React from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import Cronometro from '../components/Cronometro';
import CalculadorTabla from '../components/CalculadorTabla';
import Monedas from './Monedas';
import { Moneda } from '../model/entity/Moneda';
import AddMoneda from './AddMoneda';

export default function App() {
    return (
        <>
        <BrowserRouter>
            <Navbar />
            <Routes>
                <Route path="/" element={<Monedas/>}/>
                <Route path="/cronometro" element={<Cronometro />} />
                <Route path="/calc" element={<CalculadorTabla />} />
                <Route path="/monedas/:idmoneda" element={<Moneda />} />
                <Route path="/monedas/add" element={<AddMoneda/>}/>
            </Routes>
        </BrowserRouter>
        </>
    )

    function Navbar() {
        return (
            <nav className='menu'>
                <Link to="/"> Monedas </Link> &nbsp;
                <Link to="/cronometro"> Cronometro </Link> &nbsp;
                <Link to="/calc"> Calculadora Tabla </Link> &nbsp; 
            </nav>
        );
    }
}