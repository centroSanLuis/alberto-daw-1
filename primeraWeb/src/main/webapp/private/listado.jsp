<%@page import="com.centrosanluis.model.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% ArrayList<Usuario> listadoUsuarios = (ArrayList<Usuario>)request.getAttribute("listadoUsuarios"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listado</title>
</head>
<body>
	<table>
		<tr>
			<th>Nombre</th>
			<th>Apellidos</th>
			<th>Email</th>
			<th>Telefono</th>
			<th>Usuario</th>
		</tr>
		<%if(listadoUsuarios != null){ %>
			<%for(Usuario u : listadoUsuarios){ %>
				<tr>
					<td><%= u.getNombre() %></td>
					<td><%= u.getApellidos() %></td>
					<td><%= u.getEmail() %></td>
					<td><%= u.getTelefono() %></td>
					<td><%= u.getUsuario() %></td>
				</tr>
			<%} %>
		<%}else{ %>
			<tr>
				<td colspan="5">No hay usuarios</td>
			</tr>
		<%} %>
	</table>
</body>
</html>