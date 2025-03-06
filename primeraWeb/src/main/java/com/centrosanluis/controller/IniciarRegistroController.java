package com.centrosanluis.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centrosanluis.model.Rol;
import com.centrosanluis.service.RolService;

@WebServlet("/iniciarRegistro")
public class IniciarRegistroController extends HttpServlet{
	
	RolService rolService;
	
	@Override
	public void init() throws ServletException {
		rolService = new RolService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Rol> roles = rolService.getRoles();
		
		request.setAttribute("roles", roles);
		
		request.getRequestDispatcher("registro.jsp").forward(request, response);
	}

}
