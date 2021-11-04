<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Practica 01 Grafico Cedric Christoph</title>
    </head>
    <body>
        <h1>Login</h1>
        <p>Para poder acceder a la DDBB por favor introduzca su usuario y contraseña.
        <form action="login" method="POST">
        	<label for="user">Usuario: </label>
        	<input type="text" name="user" id="user"/>
        	<br/>
        	<label for="pwd" method="POST">Contraseña:</label>
        	<input type="password" name="pwd" id="pwd"/>
        	<br/>
        	<input type="submit" value="Acceder"/>
        </form>
        <c:if test="${not empty errorMessage}">
        	<p style="color: red;">${errorMessage}</p>
        </c:if>
        <br/>
    </body>
</html>
