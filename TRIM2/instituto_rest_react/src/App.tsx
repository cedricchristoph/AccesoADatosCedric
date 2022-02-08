import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import AddAlumno from './views/alumno/AddAlumno';
import AlumnoDetails from './views/alumno/AlumnoDetails';
import EliminarAlumno from './views/alumno/EliminarAlumno';
import Alumnos from './views/alumno/Alumnos';
import Asignaturas from './views/asignaturas/Asignaturas';


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
                <Route path="/alumnos/add" element={<AddAlumno />}/>
                <Route path="/alumnos/:dni/edit" element={<AddAlumno />} />
                <Route path="/alumnos/:dni/delete" element={<EliminarAlumno />}/>

                <Route path="/asignaturas" element={<Asignaturas />}/>
            </Routes>
        </BrowserRouter>
    </>
  );

  function Navbar() {
    return (
      <nav className='menu'>
        <div className='inline'>
          <Link to="/" type='no-link'>&nbsp;Aplicación Instituto Rest</Link>&nbsp;
          <Link to="/"> Alumnos </Link>&nbsp;
          <Link to="/asignaturas"> Asignaturas </Link>&nbsp;
        </div>
      </nav>
  );
  }

}

export default App;
