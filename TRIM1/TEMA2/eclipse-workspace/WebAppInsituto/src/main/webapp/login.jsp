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
	
        <div class="description">
            <h3>Gestionar Instituto</h3>
        </div>
        <div class="description border-round position-page-center">
        	<h3><b>Login</b></h3>
            <form action="login" method="POST" id="form-login">
            	<label for="user">Usuario </label>
            	<input type="text" name="loginuser" id="loginuser"/>
            	<br/>
            	<label for="pwd" method="POST">Contraseña </label>
            	<input type="password" name="loginpwd" id="loginpwd"/>
            	<br/>
            	<input type="submit" name="login" value="Acceder" class="btn btn-primary"/>
            </form>
        </div>
    </body>
    <footer class="page-footer">
        <div class="footer-copyright text-center">© 2021 Copyright: Cedric Christoph</div>
    </footer>
</html>