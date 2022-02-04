import React from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import Cronometro from '../components/Cronometro';
import CalculadoraTabla from '../components/CalculadorTabla';

interface IProps { }
interface IState { }
class App extends React.Component<IProps, IState>{
    constructor(props: IProps) {
        super(props);
        this.state = {};
    }
    render() {
        return (
            <>
            <p>Hola</p>
            <BrowserRouter>
                <h1>Aplicación Monedas</h1>
                <Navbar />
                <Routes>
                    <Route path="/" element={<Cronometro />} />
                    <Route path="/calculadora" element={<CalculadoraTabla />} />
                </Routes>
            </BrowserRouter>
            </>
        );
    }
}

function Navbar() {
    // visible on every page
    /*
         <BrowserRouter>
                <h1>Aplicación Monedas</h1>
                <Navbar />
                <Routes>
                    <Route path="/" element={<App />} />
                </Routes>
            </BrowserRouter>
    */
    return (
        <nav>
            <Link to="/"> Home </Link> &nbsp;
            <Link to="/calculadora"> Calculadora </Link> &nbsp;
        </nav>
    );
}
export default App;