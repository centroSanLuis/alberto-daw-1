<%@page import="com.centrosanluis.model.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%ArrayList<Rol> roles = (ArrayList<Rol>) request.getAttribute("roles"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de usuario</title>
</head>
<body>
<div class="register-container">
	<h2>Registro de usuario</h2>
	<form action="registro" method="post">
		<input type="text" name="nombre" placeholder="Nombre" required>
		<input type="text" name="apellidos" placeholder="Apellidos" required>
		<input type="text" name="email" placeholder="Correo electronico" required>
		<input type="text" name="telefono" placeholder="Telefono" required>
		<input type="text" name="usuario" placeholder="Usuario" required>
		<input type="text" name="contrasena" placeholder="Contraseña" required>
		
		<select name="rol">
			<% for(Rol rol : roles){ %>
				<option value="<%= rol.getId() %>"><%= rol.getNombre() %></option>
			<%} %>
		</select>
		
		<!-- input type="file" name="imagen" required -->
		<button type="submit">Registrarse</button>
	</form>
	<a href="login.jsp">Volver al login</a>
</div>
</body>
</html>