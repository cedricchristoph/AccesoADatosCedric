<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
    <title>Mi Perfil</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
        integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="main.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
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
                <a href="myaccount" class="nav-link"><small style="color: white;">¡Hola <c:out value="${sessionuser.user}"></c:out>!</small></a>
                <a href="logout" class="nav-link"><img src="icons/shutdown.png"/></a>
            </div>
        </nav>
        <div class="description">
            <h3><img src="icons/account32.png"/>&nbsp;Mi perfil</h3>
        </div>
        <div class="position-page-center" style="width: 75%">
            <div class="description border-round" style="width: 100;">
                <h4><b>Datos</b></h4>
                <div>
                    <small>Nombre de usuario: <b><c:out value="${sessionuser.user}"></c:out></b></small><br/>
                    <small>Tipo de cuenta   : <b><c:out value="${sessionuser.getAccessLevelStr()}"></c:out></b></small><br/>
                    <small>E-Mail vinculado : <b><c:out value="${sessionuser.email}"></c:out></b></small><br/>
                    <small>Contraseña       : <b>●●●●●●</b></small><br/>
                </div>
                <a href="cambiardatos" class="btn btn-secondary" style="margin-top: 10px;">Cambiar datos de cuenta</a>                            
                <!-- Button trigger modal -->
            <button type="button" class="btn btn-danger" style="margin-top: 10px;" data-toggle="modal" data-target="#exampleModal">
              Eliminar cuenta
            </button>
                
            </div><br/>
            <c:if test="${not empty accountsinactive}">
                <div class="description border-round usersinactive" style="width: 100%;">
                    <h4>Cuentas esperando activación</h4>
                    <table class="table table-striped">
                         <thead style="background-color: #2682bf; color: white;">
                            <tr>
                                <th scope="col">User</th>
                                <th scope="col">E-Mail</th>
                                <th scope="col">Tipo de cuenta</th>
                                <th scope="col">Activar</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="user" items="${accountsinactive}">
                            <tr>
                                <td><c:out value="${user.user}"/></td>
                                <td><c:out value="${user.email}"/></td>
                                <td><b><c:out value="${user.getAccessLevelStr()}"></c:out></b></td>
                                <td><a href="activate?user=${user.user}" class="btn btn-success">Activar</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
        </div>
        <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
              <div class="modal-dialog" role="document">
                <div class="modal-content">
                  <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Eliminar cuenta permanentemente</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                      <span aria-hidden="true">&times;</span>
                    </button>
                  </div>
                  <div class="modal-body">
                      <small>Está a punto de eliminar su cuenta permanentemente. Esta acción <b>no es reversible</b>.<br/>¿Seguro que quiere proceder?</small>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                      <a href="deleteaccount" type="button" class="btn btn-danger" >Eliminar cuenta permanentemente</a>
                  </div>
                </div>
              </div>
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