<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Instituo</title>
</head>
<body>
    <div class="col description border-round text-left">
        <h3>Alumnos</h3>
        <small>A continuacion puede ver una lista de los registros
            encontrados en la base de datos.</small>
            <br/>
        <table class="table table-striped">
            <thead style="background-color: #2682bf; color: white;">
                <tr>
                    <th scope="col">DNI</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Fecha Nacimiento</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="alumno" items="${alumnoslist}">
                <tr>
                    <td><c:out value="${alumno.dni}"/></td>
                    <td><c:out value="${alumno.nombre}"/></td>
                    <td><c:out value="${alumno.apellidos}"/></td>
                    <td><c:out value="${alumno.fechanacimiento}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    
</body>
</html>