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
                        <a class="nav-link" href="gestionarAlumnos.jsp"><img src="icons/file-earmark-person.svg"/>&nbsp;Gestionar alumnos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestionarAsignaturas.jsp"><img src="icons/book.svg"/>&nbsp;Gestionar asignaturas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="gestionarMatriculas.jsp"><img src="icons/file-earmark-text-fill.svg"/>&nbsp;Gestionar matrÃ­culas</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="description">
            <h3><img src="icons/file-earmark-text-fill.svg"/>&nbsp;Gestionar matrÃ­culas</h3>
        </div>

        <div class="row">
            <div class="col description border-round">
                <h4><b>Agregar matrÃ­cula</b></h4>
                <small>Rellene este formulario para matricular un alumno</small>
                <form class="container">
                    <label>DNI alumno</label>
                    <input type="text" name="dni" placeholder="DNI alumno*"><br/>
                    <label>AÃ±o</label>
                    <input type="text" name="year" placeholder="AÃ±o*"><br/>
                    <label>Asignaturas</label>
                    <input type="text" name="asignaturas" placeholder="Asignaturas*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Matricular</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Borrar matrÃ­cula</b></h4>
                <small>Introduzca ID de la matrÃ­cula a eliminar del sistema</small>
                <form class="container">
                    <label>ID matrÃ­cula</label>
                    <input type="text" name="idmatricula" placeholder="ID matrÃ­cula*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Borrar matrÃ­cula</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Editar matrÃ­cula</b></h4>
                <small>Rellene este formulario para editar una matrÃ­cula del sistema</small>
                <form class="container">
                    <label>ID matrÃ­cula</label>
                    <input type="text" name="idmatriculaedit" placeholder="ID matrÃ­cula*"><br/>
                    <label>DNI alumno</label>
                    <input type="text" name="dniedit" placeholder="DNI alumno*"><br/>
                    <label>AÃ±o</label>
                    <input type="text" name="yearedit" placeholder="AÃ±o*"><br/>
                    <label>Asignaturas</label>
                    <input type="text" name="asignaturasedit" placeholder="Asignaturas*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Actualizar</a>
                    </div>
                </form>
            </div>
            <div class="col description border-round">
                <h4><b>Mostrar matriculas</b></h4>
                <small>Introduzca los datos para mostrar matrÃ­culas de un alumno</small>
                <form class="container">
                    <label>AÃ±o</label>
                    <input type="text" name="yearmostrar" placeholder="AÃ±o*"><br/>
                    <label>DNI alumno</label>
                    <input type="text" name="dnimostrar" placeholder="DNI alumno*"><br/>
                    <div class="text-center text-md-left container">
                        <a class="btn btn-primary position-bottom-right" onclick="document.getElementById('contact-form').submit();">Mostrar</a>
                    </div>
                </form>
            </div>
        </div>
        <div style="margin: auto; width: 50%;">
            â<textarea id="txtArea" rows="5" cols="100" class="container"></textarea>
        </div>
    </body>
    <footer class="page-footer">
        <div class="footer-copyright text-center">Â© 2021 Copyright: Cedric Christoph</div>
    </footer>
</html>
