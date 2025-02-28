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
<h1>HOLA, <%= usuario.getUsuario() %></h1>
</body>
</html>