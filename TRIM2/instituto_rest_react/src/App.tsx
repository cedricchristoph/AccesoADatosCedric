import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import AlumnoDetails from './views/AlumnoDetails';
import Alumnos from './views/Alumnos';

function App() {

/*
                <Route path="/alumnos/add" element={<AddAlumno/>}/>
                <Route path="/alumnos/edit" element={<AlumnoDetails/>}/>
                <Route path="/alumnos/:dni/matriculas/:id" element={<MatriculaDetails/>}/>
                <Route path="/asignaturas" element={<Asignaturas/>}/>
                <Route path="/asignaturas/:id" element={<AsignaturaDetails/>}/>
                <Route path="/asignaturas/add" element={<AsignaturaDetails/>}/>
*/

  return(
    <>
        <BrowserRouter>
            <Navbar />
            <Routes>
                <Route path="/" element={<Alumnos/>}/>
                <Route path="/alumnos" element={<Alumnos/>}/>
                <Route path="/alumnos/:id" element={<AlumnoDetails />}/>
            </Routes>
        </BrowserRouter>
    </>
  );

  function Navbar() {
    return (
      <nav className='menu'>
          <Link to="/"> Alumnos </Link>&nbsp;
          <Link to="/asignaturas"> Asignaturas </Link>&nbsp;
      </nav>
  );
  }

}

export default App;
