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
				<label>${mensaje}</label><br/>		
			</c:forEach>
		</div>

		<ul class="conectados">
		<label>Conectados</label>
			<c:forEach var="u" items="${connected}">
				<li>${u}</li>
			</c:forEach>
		</ul>


		<form action="enviarmensaje" method="POST">
			<label for="nombre">Nombre: </label> <input type="text" name="nombre"
				id="nombre" value="" />
			<textarea name="mensaje" id="mensaje" cols="20" rows="10"></textarea>
			<input type="submit" value="Enviar" />
		</form>
	</div>






</body>
</html>
