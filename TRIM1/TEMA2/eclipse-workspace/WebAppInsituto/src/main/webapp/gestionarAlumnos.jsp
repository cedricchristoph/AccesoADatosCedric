<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>Gestionar Instituto // Gestionar Alumnos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    
    <body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
        <nav class="navbar navbar-expand-md">
            <a class="navbar-brand" href="index.jsp"><img src="icons/building.svg"/>&nbsp;Gestionar Instituto</a>
            <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="main-navigation">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp"><img src="icons/house.svg"/>&nbsp;Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="alumnos"><img src="icons/file-earmark-person.svg"/>&nbsp;Gestionar alumnos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="asignaturas"><img src="icons/book.svg"/>&nbsp;Gestionar asignaturas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="matriculas"><img src="icons/file-earmark-text-fill.svg"/>&nbsp;Gestionar matrÃ­culas</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="description">
            <h3><img src="icons/file-earmark-person.svg"/>&nbsp;Gestionar alumnos</h3>
        </div>

        <div class="row">
            <div class="col description border-round">
                <h4><b>Agregar alumno</b></h4>
                <small>Rellene este formulario para registrar un nuevo alumno al sistema</small>
                <form id="alumno-agregar-form" class="container" action="agregaralumno" method="POST">
                    <label>DNI</label>
                    <input type="text" name="agregaralumnodni" placeholder="DNI*"><br/>
                    <label>Nombre</label>
                    <input type="text" name="agregaralumnonombre" placeholder="Nombre*"><br/>
                    <label>Apellidos</label>
                    <input type="text" name="agregaralumnoapellidos" placeholder="Apellidos"><br/>
                    <label>Nacimiento</label>
                    <input type="text" name="agregaralumnofecha" placeholder="Fecha de nacimiento"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('alumno-agregar-form').submit();">Agregar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Borrar alumno</b></h4>
                <small>Introduzca DNI del alumno a eliminar del sistema</small>
                <form class="container" action="deleteAlumno" method="POST">
                    <label>DNI</label>
                    <input type="text" name="dni" placeholder="DNI*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Borrar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Editar alumno</b></h4>
                <small>Rellene este formulario para editar un alumno ya registrado en el sistema</small>
                <form class="container" action="updateAlumno" method="POST">
                    <label>DNI</label>
                    <input type="text" name="dni" placeholder="DNI*"><br/>
                    <label>Nombre</label>
                    <input type="text" name="nombre" placeholder="Nombre*"><br/>
                    <label>Apellidos</label>
                    <input type="text" name="apellidos" placeholder="Apellidos"><br/>
                    <label>Nacimiento</label>
                    <input type="text" name="fechaNacimiento" placeholder="Fecha de nacimiento"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Actualizar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Mostrar alumno</b></h4>
                <small>Introduzca los datos del alumno a mostrar</small>
                <form class="container" action="showAlumno" method="POST">
                    <label>DNI</label>
                    <input type="text" name="dni" placeholder="DNI*"><br/>
                    <label>Nombre</label>
                    <input type="text" name="nombre" placeholder="Nombre*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Mostrar</a>
                    </div>
                </form>
            </div>
        </div>
        <div style="margin: auto; width: 50%;">
            <textarea id="txtArea" rows="5" cols="100" class="container"></textarea>
        </div>
        
        <c:if test="${not empty message}">
        	<div class="popup-green">
        		<p>${message}</p>
        	</div>
        </c:if>
        <c:if test="${not empty message}">
        	<div class="popup-red">
        		<p>${errorMessage}</p>
        	</div>
        </c:if>
    </body>  
    
    <footer class="page-footer">
        <div class="footer-copyright text-center">© 2021 Copyright: Cedric Christoph</div>
    </footer>
</html>
