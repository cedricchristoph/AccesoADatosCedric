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
        <h1>Buscar</h1>
        <p>A continuacion puedes introducir una marca de lapices a buscar en la DDBB</p>
        <form action="search" method="POST">
        	<label for="marca">Marca: </label>
        	<input type="text" name="marca" id="marca"/>
        	<br/>
        	<input type="submit" value="Buscar"/>
        </form>
        <c:if test="${not empty errorMessage}">
        	<p style="color: red;">${errorMessage}</p>
        </c:if>
        <br/>
        <c:if test="${not empty resultados}">
        	<h3>Resultados</h3>
        	<c:forEach var="r" items="${resultados}">
        		<p>${r}</p>
        	</c:forEach>
        </c:if>
    </body>
</html>