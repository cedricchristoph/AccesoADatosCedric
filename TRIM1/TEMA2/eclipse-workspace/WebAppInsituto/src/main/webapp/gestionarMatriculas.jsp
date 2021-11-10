<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>Gestionar Instituto // Gestionar Matriculas</title>
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
            </div>
        </nav>

        <div class="description">
            <h3><img src="icons/file-earmark-text-fill.svg"/>&nbsp;Gestionar matrí­culas</h3>
        </div>

        <div class="row">
            <div class="col description border-round">
                <h4><b>Agregar matrícula</b></h4>
                <small>Rellene este formulario para matricular un alumno</small>
                <form class="container">
                    <label>DNI alumno</label>
                    <input type="text" name="dni" placeholder="DNI alumno*"><br/>
                    <label>Año</label>
                    <input type="text" name="year" placeholder="Año*"><br/>
                    <label>Asignaturas</label>
                    <input type="text" name="asignaturas" placeholder="Asignaturas*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Matricular</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Borrar matrícula</b></h4>
                <small>Introduzca ID de la matrí­cula a eliminar del sistema</small>
                <form class="container">
                    <label>ID matrí­cula</label>
                    <input type="text" name="idmatricula" placeholder="ID matrícula*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Borrar matrícula</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Editar matrí­cula</b></h4>
                <small>Rellene este formulario para editar una matrí­cula del sistema</small>
                <form class="container">
                    <label>ID matrícula</label>
                    <input type="text" name="idmatriculaedit" placeholder="ID matrí­cula*"><br/>
                    <label>DNI alumno</label>
                    <input type="text" name="dniedit" placeholder="DNI alumno*"><br/>
                    <label>Año</label>
                    <input type="text" name="yearedit" placeholder="Año*"><br/>
                    <label>Asignaturas</label>
                    <input type="text" name="asignaturasedit" placeholder="Asignaturas*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Actualizar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Mostrar matriculas</b></h4>
                <small>Introduzca los datos para mostrar matrículas de un alumno</small>
                <form class="container">
                    <label>Año</label>
                    <input type="text" name="yearmostrar" placeholder="Año*"><br/>
                    <label>DNI alumno</label>
                    <input type="text" name="dnimostrar" placeholder="DNI alumno*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Mostrar</a>
                    </div>
                </form>
            </div>
        </div>
        <div style="margin: auto; width: 50%;">
            <textarea id="txtArea" rows="5" cols="100" class="container"></textarea>
        </div>
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
