import React from 'react';
import { BrowserRouter, Link, Route, Routes, useNavigate } from 'react-router-dom';
import { RequireAuth } from './components/RequireAuth';
import './style/App.css';
import ApiConnectionError from './views/ApiConnectionError';
import Login from './views/login';
import { useState } from 'react';
import Logout from './views/Logout';
import VerCarta from './views/public/VerCarta';
import Mesas from './views/authorized_user/Mesas';
import MesaDetails from './views/authorized_user/MesaDetails';
import ServicioDetails from './views/authorized_user/ServicioDetails';
import RealizarPedido from './views/authorized_user/RealizarPedido';
function App() {  

    const[tokenState, setTokenState] = useState<String>("");

    return(
      <>
          <BrowserRouter>
              <Navbar />
              <Routes>                
                  <Route path="/connection_error" element={<ApiConnectionError/>}/>
                  <Route path="/login" element={<Login setTokenState={setTokenState}/>}/>
                  <Route path="/logout" element={<Logout setTokenState={setTokenState}/>}/>
                  <Route path="/carta" element={<VerCarta/>}/>
                  <Route path="/" element={<VerCarta/>}/>

                  <Route path="/mesas" element={<RequireAuth><Mesas/></RequireAuth>}/>
                  <Route path="/mesas/:mesaid" element={<RequireAuth><MesaDetails/></RequireAuth>}/>
                  <Route path="/mesas/:mesaid/servicios/:servicioid" element={<RequireAuth><ServicioDetails/></RequireAuth>}/>
                  <Route path="/platos" element={<RequireAuth><></></RequireAuth>}/>
                  <Route path="/mesas/:mesaid/servicios/:servicioid/add" element={<RequireAuth><RealizarPedido/></RequireAuth>}/>
              </Routes>
          </BrowserRouter>
      </>
    );
  
    function Navbar() {
      let autorizado = localStorage.getItem("token");
      return (
        <nav className='menu'>
          {autorizado != null ? 
          <>
          <div>
            <Link to="/" type='no-link'>Mi Restaurante</Link>
            <Link to="/mesas"> Mesas </Link>
            <Link to="/platos"> Platos </Link>
            <Link to="/logout"> Cerrar sesión </Link>
          </div>
          </> 
          
          : 

          <>
          <div>
            <Link to="/" type='no-link'>Mi Restaurante</Link>
            <Link to="/carta">Ver carta</Link>
            <Link to="/disponibilidad">Ver disponibilidad</Link>
            <Link to="/login">Iniciar sesión</Link>
          </div>
          </>

          }
        </nav>
    );
    }
  
  }

export default App;
