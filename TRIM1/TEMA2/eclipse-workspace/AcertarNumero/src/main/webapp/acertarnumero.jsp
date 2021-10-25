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
<title>Acertar Numero: Jugar</title>
</head>

<body>
	<div class="top">
		<h2>Acertar Numero</h2>
		<p>¡Averigua el número secreto que se está buscando!</p>
		<p>${sessionuser}, ¿podrás ser más rápido que los demás?</p>
	</div>
	<form action="averiguar" method="POST">
		<label for="numero">Número:</label>
		<input name="numero" id="numero" value="" type="text"/>
		<input name="enviar" id="enviar" type="submit"/>
	</form>
	<div class="numeros">
	
	</div>
</body>

</html>