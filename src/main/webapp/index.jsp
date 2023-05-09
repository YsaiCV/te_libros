<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : index
    Created on : 4 may. de 2023, 17:59:10
    Author     : Ysai
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Libro"%>
<%
    List<Libro> lista = (List<Libro>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <h1>Listado de libros</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="1">
            <thead>
            <tr>
                <th>ID</th>
                <th>ISBN</th>
                <th>Titulo</th>
                <th>Categoria</th>
                <th></th>     
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${lista}">
            <tr>
                <td>${item.id}</td>
                <td>${item.isbn}</td>
                <td>${item.titulo}</td>
                <td>${item.categoria}</td>
                <td><a href="MainController?op=eliminar&id=${item.id}" onclick="return(confirm('Esta seguro?')))">Eliminar</a></td>
                <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
            </tr>
            </c:forEach>
            </tbody>
            
        </table>
    </body>
</html>
