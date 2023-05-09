<%-- 
    Document   : editar
    Created on : 4 may. de 2023, 17:59:21
    Author     : Ysai
--%>

<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Libro lib = (Libro) request.getAttribute("lib");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%if(lib.getId() == 0){%>
            <h1>Nuevo Libro</h1>
        <%}else{%>
            <h1>Editar Libro</h1>
        <%}%>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${lib.id}">
            <table>
                <tr>
                    <td>ISBN</td>
                    <td><input type="text" name="isbn" value="${lib.isbn}"></td>
                </tr>
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${lib.titulo}"></td>
                </tr>
                <tr>
                    <td>Categoria</td>
                    <td><input type="text" name="categoria" value="${lib.categoria}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit"></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>
