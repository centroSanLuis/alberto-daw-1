package com.centrosanluis.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListadoController
 */
@WebServlet("/listadoUsuarios")
public class ListadoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//llamo al service para obtener todos los usuarios
		
		//añado en la request el atributo (no sesion) listado usuarios
		
		//encadeno la peticion y la envio a listado.jsp
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


}
