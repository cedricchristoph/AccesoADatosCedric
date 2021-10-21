<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>

<meta charset="utf-8" />
<meta name="author" content="juan carlos p.r." />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="refresh" content="20">
<title>foro web</title>


<link rel="stylesheet" href="css/estilos.css">

</head>
<body>
	<h1 class="cabecera">IRC Foro</h1>
	<div class="principal">
		
		<div class="foro">
			<c:forEach var="mensaje" items="${mensajes}">
				<div>${mensaje}</div>	
			</c:forEach>
		</div>

		<div class="conectados">
			<h4>Conectados</h4>
			<ul>
				<c:forEach var="u" items="${connected}">
					<li class="nombreUsuario">${u}</li>
				</c:forEach>
			</ul>
		</div>

		<form class="form" action="enviarmensaje" method="POST">
			<label for="nombre">Nombre: </label> 
			<c:choose>
				<c:when test="${empty user}">
					<input type="text" name="nombre" id="nombre" value=""/>
				</c:when>
				<c:otherwise>
					<span> ${user} </span>
				</c:otherwise>
			</c:choose>
			<textarea name="mensaje" id="mensaje" cols="20" rows="10"></textarea>
			<input class="btn-primary" type="submit" value="Enviar" />
			<a href="disconnect" class="btn-primary">Desconectar</a>
		</form>
	</div>
</body>
</html>
