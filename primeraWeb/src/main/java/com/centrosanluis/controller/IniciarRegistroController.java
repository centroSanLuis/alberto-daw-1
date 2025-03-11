package com.centrosanluis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centrosanluis.model.Rol;
import com.centrosanluis.model.Usuario;
import com.centrosanluis.service.RolService;
import com.centrosanluis.service.UsuarioService;

@WebServlet("/iniciarRegistro")
public class IniciarRegistroController extends HttpServlet{
	
	RolService rolService;
	UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		rolService = new RolService();
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuario = request.getParameter("usuario");
		
		if(usuario != null) {
			//quiero obtener el usuario de la BD
			Usuario u = usuarioService.getUserByUsuario(usuario);
			//quiero añadir ese usuario como atributo a la request
			request.setAttribute("usuario", u);
		}
		
		List<Rol> roles = rolService.getRoles();
		request.setAttribute("roles", roles);
		
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}

}
