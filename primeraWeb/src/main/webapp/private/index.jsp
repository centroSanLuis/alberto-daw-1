<%@page import="com.centrosanluis.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% Usuario usuario = (Usuario)request.getSession().getAttribute("usuario"); %>
<h1>HOLA, <%= usuario.getUsuario() %> (<%= usuario.getRol().getNombre() %>)</h1>
<p><a href="../listadoUsuarios">Listado Usuarios</a></p>
<% if(usuario.getRol().getId() == 1){ %>
	<p><a href="crearRol.jsp">Crear Rol</a></p>
<% } %>
</body>
</html>