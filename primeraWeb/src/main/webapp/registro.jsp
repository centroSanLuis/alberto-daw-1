<%@page import="com.centrosanluis.model.Usuario"%>
<%@page import="com.centrosanluis.model.Rol"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%ArrayList<Rol> roles = (ArrayList<Rol>) request.getAttribute("roles"); %>
<%Usuario usuario = (Usuario) request.getAttribute("usuario"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de usuario</title>
</head>
<body>
<div class="register-container">
	<h2>
		<%if(usuario != null){ %>
			Editar usuario
		<%}else{ %>
			Alta de usuario
		<%} %>
	</h2>
	<form action="<%=usuario!=null?"editarUsuario":"registro" %>" method="post">
		<input type="text" name="nombre" value="<%= usuario!=null?usuario.getNombre():"" %>" placeholder="Nombre" required>
		<input type="text" name="apellidos" value="<%= usuario!=null?usuario.getApellidos():"" %>" placeholder="Apellidos" required>
		<input type="text" name="email" value="<%= usuario!=null?usuario.getEmail():"" %>" placeholder="Correo electronico" required>
		<input type="text" name="telefono" value="<%= usuario!=null?usuario.getTelefono():"" %>" placeholder="Telefono" required>
		<input type="text" 
			name="usuario" 
			value="<%= usuario!=null?usuario.getUsuario():"" %>" 
			<%= usuario!=null?"readonly":"" %>
			placeholder="Usuario" 
			required>
		
		<%if(usuario == null){ %>
			<input type="text" name="contrasena" placeholder="Contraseña" required>
		<%} %>
		
		<select id="rol" name="rol">
			<% for(Rol rol : roles){ %>
				<option value="<%= rol.getId() %>"><%= rol.getNombre() %></option>
			<%} %>
		</select>
		
		<!-- input type="file" name="imagen" required -->
		<button type="submit">
			<%if(usuario != null){ %>
				Editar Usuario
			<%}else{ %>
		 		Crear Usuario
		 	<%} %>
		 </button>
	</form>
	<%if(usuario != null){ %>
		<a href="private/index.jsp">Volver al index</a>
	<%}else{ %>
		<a href="login.jsp">Volver al login</a>
	<%} %>
	
</div>
<script>
	<%if(usuario != null){ %>
		document.getElementById("rol").value=<%= usuario.getRol().getId() %>
	<%}%>
</script>
</body>
</html>