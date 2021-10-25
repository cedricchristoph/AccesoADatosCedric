<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8" />
<meta name="author" content="Cedric Christoph" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link type="stylesheet" href="css/estilos.css"/>
<title>Acertar Numero: Registrar</title>
</head>

<body>
	<div class="top">
		<h2>Registrar</h2>
		<p>Para poder participar en el juego es necesario que se registre.</p>
	</div>
	<form action="registrarusuario" method="POST">
		<label for="nombre">Nombre:</label>
		<input name="nombre" id="nombre" value="" type="text"/>
		<input name="enviar" id="enviar" type="submit"/>
	</form>
</body>

</html>
