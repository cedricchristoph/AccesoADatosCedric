<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>Gestionar Instituto</title>
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
            <h3><img src="icons/building.svg"/>&nbsp;Gestionar Instituto</h3>
        </div>
        <div class="description border-round position-page-center">
            <a href="gestionarAlumnos.jsp">
                <div class= "selection" type="button">
                    <img src="icons/file-earmark-person.svg"/>
                    <h4>Gestionar alumnos</h4>
                </div>
            </a>
            <a href="#">
                <div class="selection" type="button">
                    <img src="icons/book.svg"/>
                    <h4>Gestionar asignaturas</h4>
                </div>
            </a>
            <a href="gestionarMatriculas.jsp">
                <div class="selection" type="button">
                    <img src="icons/file-earmark-text-fill.svg"/>
                    <h4>Gestionar matrÃ­culas</h4>
                </div>
            </a>
        </div>
    </body>
    <footer class="page-footer">
        <div class="footer-copyright text-center">Â© 2021 Copyright: Cedric Christoph</div>
    </footer>
</html>