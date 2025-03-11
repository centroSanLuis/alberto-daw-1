package com.centrosanluis.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centrosanluis.model.Rol;
import com.centrosanluis.model.Usuario;
import com.centrosanluis.service.UsuarioService;

@WebServlet("/editarUsuario")
public class EditarUsuarioController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		String user = request.getParameter("usuario");
		int idRol = Integer.valueOf(request.getParameter("rol"));
		
		Rol rol = new Rol();
		
		rol.setId(idRol);

		Usuario usuario = new Usuario(nombre, apellidos, email, telefono, user, "", rol);
		
		if(usuarioService.updateUsuario(usuario)) {
			response.sendRedirect("private/index.jsp");
		}else {
			//devolver a la pantalla de editar
		}
	}
}
