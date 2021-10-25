<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta charset="utf-8" />
<meta name="author" content="Cedric Christoph" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link type="stylesheet" href="css/estilos.css" />
<title>Acertar Numero: Jugar</title>
</head>

<body>
	<div class="top">
		<h2>Acertar Numero</h2>
		<c:if test="${ganador}">
			<h4><b>¡Enhorabuena!</b></h4>
			<p>¡Has averiguado el número secreto!</p>
			<p>Numero: <b>${lastnumber}</b></p>
			<form action="averiguar" method="GET">
				<input type="submit" name="replay" value="Seguir averiguando"/>
			</form>
			<a href="/AcertarNumero/disconnect" class="button">Salir</a>
		</c:if>
		<c:if test="${not ganador}">
			<p>¡Averigua el número secreto que se está buscando!</p>
			<p>${sessionuser}, ¿podrás ser más rápido que los demás?</p>
			<form action="averiguar" method="POST">
				<label for="numero">Número:</label> <input name="numero" id="numero"
					value="" type="text" /> 
					<input name="enviar" id="enviar" type="submit" />
			</form>
			<div class="numeros">
				<c:forEach var="num" items="${numeros}">
					<c:choose>
						<c:when test="${num.number lt numerosecreto.number}">
							<p>${num} es <b>menor</b> que el secreto</p>
						</c:when>
						<c:otherwise>
							<p>${num} es <b>mayor</b> que el secreto</p>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</c:if>
	</div>
</body>

</html>