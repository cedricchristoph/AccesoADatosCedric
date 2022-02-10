import React from 'react';
import { BrowserRouter, Link, Route, Routes } from 'react-router-dom';
import AddAlumno from './views/alumno/AddAlumno';
import AlumnoDetails from './views/alumno/AlumnoDetails';
import EliminarAlumno from './views/alumno/EliminarAlumno';
import Alumnos from './views/alumno/Alumnos';
import Asignaturas from './views/asignaturas/Asignaturas';
import EditAlumno from './views/alumno/EditAlumno';
import AddMatricula from './views/matriculas/AddMatricula';
import EliminarMatricula from './views/matriculas/EliminarMatricula';
import AddAsignatura from './views/asignaturas/AddAsignatura';
import EliminarAsignatura from './views/asignaturas/EliminarAsignatura';
import ApiConnectionError from './views/ApiConnectionError';


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
                <Route path="/connection_error" element={<ApiConnectionError/>}/>

                <Route path="/" element={<Alumnos/>}/>

                <Route path="/alumnos" element={<Alumnos/>}/>
                <Route path="/alumnos/:id" element={<AlumnoDetails />}/>
                <Route path="/alumnos/add" element={<AddAlumno />}/>
                <Route path="/alumnos/:dni/edit" element={<EditAlumno />} />
                <Route path="/alumnos/:dni/delete" element={<EliminarAlumno />}/>
                <Route path="/alumnos/:dni/matriculas/add" element={<AddMatricula />} />
                <Route path="/alumnos/:dni/matriculas/:id/delete" element={<EliminarMatricula/>}/>
                
                <Route path="/asignaturas" element={<Asignaturas />}/>
                <Route path="/asignaturas/add" element={<AddAsignatura/>}/>
                <Route path="/asignaturas/:id/delete" element={<EliminarAsignatura/>}/>
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
