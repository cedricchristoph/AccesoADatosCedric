<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>Alumnos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    
    <body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
        <nav class="navbar navbar-expand-md">
            <a class="navbar-brand" href="inicio"><img src="icons/building.svg"/>&nbsp;Gestionar Instituto</a>
            <button class="navbar-toggler navbar-dark" type="button" data-toggle="collapse" data-target="#main-navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="main-navigation">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="inicio"><img src="icons/house.svg"/>&nbsp;Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="alumnos"><img src="icons/file-earmark-person.svg"/>&nbsp;Gestionar alumnos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="asignaturas"><img src="icons/book.svg"/>&nbsp;Gestionar asignaturas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="matriculas"><img src="icons/file-earmark-text-fill.svg"/>&nbsp;Gestionar matrículas</a>
                    </li>
                </ul>
                <small style="color: white; margin-left: 20px;">¡Hola <c:out value="${sessionuser.user}"></c:out>!&nbsp;&nbsp;</small>
                <a href="logout" class="nav-link"><img src="icons/shutdown.png"/></a>
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
                <form class="container" action="borraralumno" method="POST" id="alumno-borrar-form">
                    <label for="borraralumnodni">DNI</label>
                    <input type="text" name="borraralumnodni" placeholder="DNI*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('alumno-borrar-form').submit();">Borrar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Editar alumno</b></h4>
                <small>Rellene este formulario para editar un alumno ya registrado en el sistema</small>
                <form class="container" action="editaralumno" method="POST" id="alumno-editar-form">
                    <label>DNI</label>
                    <input type="text" name="editaralumnodni" placeholder="DNI*"><br/>
                    <label>Nombre</label>
                    <input type="text" name="editaralumnonombre" placeholder="Nombre*"><br/>
                    <label>Apellidos</label>
                    <input type="text" name="editaralumnoapellidos" placeholder="Apellidos"><br/>
                    <label>Nacimiento</label>
                    <input type="text" name="editaralumnofecha" placeholder="Fecha de nacimiento"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('alumno-editar-form').submit();">Actualizar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4>Mostrar alumno</h4>
                <small>Introduzca los datos del alumno a mostrar</small><br/>
                <form class="container" action="mostraralumno" method="POST" id="alumno-buscar-form">
                    <label for="buscaralumnodni">DNI</label>
                    <input type="text" name="buscaralumnodni" placeholder="DNI"><br/>
                    <label for="buscaralumnonombre">Nombre</label>
                    <input type="text" name="buscaralumnonombre" placeholder="Nombre"><br/>
                    <div class="text-center text-md-left container position-bottom-right">
                        <a class="btn btn-primary" onclick="document.getElementById('alumno-buscar-form').submit();">Mostrar</a>
                        <a class="btn btn-primary" href="alumnos">Reset</a>
                    </div>
                </form>
            </div>
        </div>

        <div class="col description border-round text-left">
            <h3>Registros</h3>
            <small>A continuacion puede ver una lista de los registros
                encontrados en la base de datos.</small>
                <br/>
            <table class="table table-striped">
                <thead style="background-color: #2682bf; color: white;">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Fecha Nacimiento</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="alumno" items="${alumnoslist}">
                    <tr>
                        <td><c:out value="${alumno.dni}"/></td>
                        <td><c:out value="${alumno.nombre}"/></td>
                        <td><c:out value="${alumno.apellidos}"/></td>
                        <td><c:out value="${alumno.getSimpleDate()}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <br/><br><br/>
    </body>
    <footer class="page-footer">
    	<c:if test="${not empty message}">
        	<div class="popup-green position-page-center border-round">
        		<p>${message}</p>
        	</div>
        </c:if>
        
        <c:if test="${not empty infoMessage}">
        	<div class="popup-blue position-page-center border-round">
        		<p>${infoMessage}</p>
        	</div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
        	<div class="popup-red position-page-center border-round">
        		<p>${errorMessage}</p>
        	</div>
        </c:if>
        <div class="footer-copyright text-center">© 2021 Copyright: Cedric Christoph</div>
    </footer>
</html>
