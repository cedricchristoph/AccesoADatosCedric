<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hola</title>
</head>
<body>
	<p>¡Bienvenido!</p>
	<p>Esta es la lista:</p>
	<ul>
		<c:forEach var="item" items="${lista}">
			<li>
				<c:out value="${item}"></c:out>
			</li>
		</c:forEach>
	</ul>
</body>
</html>