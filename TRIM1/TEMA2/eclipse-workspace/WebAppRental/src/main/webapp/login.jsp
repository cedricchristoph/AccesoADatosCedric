<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <title>Film Rental</title>
        <link rel="shortcut icon" href="icons/film-2-16.ico">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    

	<body>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	
        <div class="description">
            <h3><img src="icons/film-2-48.png" style="margin-right: 12px;"/>Film Rental</h3>
        </div>
        <div class="description border-round position-page-center">
        	<h3><img src="icons/account-login-32.png" style="margin-right: 12px;"/>Login</h3>
            <form action="login" method="POST" id="form-login">
            	<label for="user"><img src="icons/user-24.png" style="margin-right: 8px;"/>Usuario</label>
            	<br/>
            	<input type="text" name="loginuser" id="loginuser"/>
            	<br/>
            	<br/>
            	<label for="pwd"><img src="icons/key-3-24.png" style="margin-right: 8px;"/>Contraseña</label>
            	<br/>
            	<input type="password" name="loginpwd" id="loginpwd"/>
            	<br/><br/><br/>
            	<input type="submit" name="login" value="Acceder" class="btn btn-warning"/>
            </form>
            <br/><br/>
            <a href="registrar" class="btn btn-dark" style="width: 99%">Crear cuenta</a>
        </div>

        <c:if test="${not empty message }">
            <div id="message">    
                <div id="inner-message" class="alert alert-success alert-dismissible fade show" role="alert" style="width: 30%;">
                    ${message}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </c:if>
        
        <c:if test="${not empty infoMessage}">
            <div class="alert alert-info alert-dismissible fade show" role="alert">
                ${infoMessage}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${errorMessage}
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>

    </body>
    <footer class="page-footer">
        <div class="footer-copyright text-center">© 2021 Copyright: Cedric Christoph</div>
    </footer>
</html>