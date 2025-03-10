package com.centrosanluis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centrosanluis.model.Usuario;
import com.centrosanluis.service.UsuarioService;

@WebServlet("/borrarUsuario")
public class BorrarUsuarioController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		
		Usuario u = new Usuario();
		
		u.setUsuario(usuario);
		
		if(usuarioService.borrarUsuario(u)) {
			response.sendRedirect("listadoUsuarios");
		}else {
			List<Usuario> listadoUsuarios = usuarioService.getUsers();
			
			request.setAttribute("listadoUsuarios", listadoUsuarios);
			request.setAttribute("error", "El usuario no ha podido darse de baja");
			
			request.getRequestDispatcher("private/listado.jsp").forward(request, response);
		}
	}

}
