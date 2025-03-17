<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<c:choose>
			<c:when test="${usuario != null}">
				Editar usuario
			</c:when>
			<c:otherwise>
				Alta de usuario
			</c:otherwise>
		</c:choose>
	</h2>
	<form action='${usuario!=null?"editarUsuario":"registro"}' method="post">
		<input type="text" name="nombre2" value="${usuario!=null?usuario.getNombre():''}" placeholder="Nombre" required>
		<input type="text" name="apellidos" value="${usuario!=null?usuario.getApellidos():''}" placeholder="Apellidos" required>
		<input type="text" name="email" value="${usuario!=null?usuario.getEmail():''}" placeholder="Correo electronico" required>
		<input type="text" name="telefono" value="${usuario!=null?usuario.getTelefono():''}" placeholder="Telefono" required>
		<input type="text" 
			name="usuario" 
			value="${usuario!=null?usuario.getUsuario():''}" 
			${ usuario!=null?"readonly":'' }
			placeholder="Usuario" 
			required>
		
		<c:if test="${usuario == null}">
			<input type="text" name="contrasena" placeholder="Contraseña" required>
		</c:if>
		
		<select id="rol" name="rol">
			<c:forEach items="${roles}" var="rol">
				<option value="${rol.id}">${rol.nombre}</option>
			</c:forEach>
		</select>
		
		<!-- input type="file" name="imagen" required -->
		<button type="submit">
			<c:choose>
				<c:when test="${usuario != null}">
					Editar Usuario
				</c:when>
				<c:otherwise>
					Crear Usuario
				</c:otherwise>
			</c:choose>
		 </button>
	</form>
	
	<c:choose>
		<c:when test="${usuario != null }">
			<a href="private/index.jsp">Volver al index</a>
		</c:when>
		<c:otherwise>
			<a href="login.jsp">Volver al login</a>
		</c:otherwise>
	</c:choose>
</div>
<script>
	<c:if test="${usuario != null }">
		document.getElementById("rol").value=${usuario.rol.id}
	</c:if>
</script>
</body>
</html>