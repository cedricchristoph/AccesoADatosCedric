<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>Matriculas</title>
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
            <h3><img src="icons/file-earmark-text-fill.svg"/>&nbsp;Gestionar matrí­culas</h3>
        </div>

        <div class="row">
            <div class="col description border-round">
                <h4><b>Agregar matrícula</b></h4>
                <small>Rellene este formulario para matricular un alumno</small>
                <form id="matricula-agregar-form" class="container" action="agregarmatricula" method="POST">
                    <label for="matriculaagregardni">DNI alumno</label>
                    <input type="text" name="matriculaagregardni" placeholder="12345678A"><br/>
                    <label for="matriculaagregaryear">Año</label>
                    <input type="text" name="matriculaagregaryear" placeholder="2003"><br/>
                    <label for="matriculaagregaryear">Asignaturas</label>
                    <input type="text" name="matriculaagregarasignaturas" placeholder="1,2,3,..."><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('matricula-agregar-form').submit();">Matricular</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Borrar matrícula</b></h4>
                <small>Introduzca ID de la matrícula a eliminar del sistema</small>
                <form id="matricula-borrar-form" class="container" action="borrarmatricula" method="POST">
                    <label for="matriculaborrarid">ID matrícula</label>
                    <input type="text" name="matriculaborrarid" placeholder="12"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('matricula-borrar-form').submit();">Borrar matrícula</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Editar matrícula</b></h4>
                <small>Rellene este formulario para editar una matrícula del sistema</small>
                <form id="matricula-editar-form" class="container" action="editarmatricula" method="POST">
                    <label for="matriculaeditarid">ID matrícula</label>
                    <input type="text" name="matriculaeditarid" placeholder="12"><br/>
                    <label for="matriculaeditardni">DNI alumno</label>
                    <input type="text" name="matriculaeditardni" placeholder="12345678A"><br/>
                    <label for="matriculaeditaryear">Año</label>
                    <input type="text" name="matriculaeditaryear" placeholder="2003"><br/>
                    <label for="matriculaeditarasignaturas">Asignaturas (IDs)</label>
                    <input type="text" name="matriculaeditarasignaturas" placeholder="1,2,3,..."><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('matricula-editar-form').submit();">Actualizar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Mostrar matriculas</b></h4>
                <small>Introduzca los datos para mostrar matrículas de un alumno</small>
                <form id="matricula-mostrar-form" class="container" action="mostrarmatricula" method="POST">
                    <label for="matriculamostraryear">Año</label>
                    <input type="text" name="matriculamostraryear" placeholder="2003"><br/>
                    <label for="matriculamostrardni">DNI alumno</label>
                    <input type="text" name="matriculamostrardni" placeholder="12345678A"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('matricula-mostrar-form').submit();">Mostrar</a>
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
                        <th scope="col">DNI</th>
                        <th scope="col">Año</th>
                        <th scope="col">Asignaturas</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="matricula" items="${matriculaslist}">
                    <tr>
                        <td><c:out value="${matricula.id}"/></td>
                        <td><c:out value="${matricula.alumno.dni}"/></td>
                        <td><c:out value="${matricula.year}"/></td>
                        <td>
                            <c:forEach var="asignatura" items="${matricula.asignaturas}">
                                <c:out value="${asignatura.nombre}"></c:out>&nbsp;
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
    <br/><br/><br/>
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
