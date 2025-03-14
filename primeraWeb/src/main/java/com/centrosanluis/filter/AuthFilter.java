package com.centrosanluis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.centrosanluis.model.Usuario;
import com.centrosanluis.service.UsuarioService;

@WebFilter("/private/*")
public class AuthFilter implements Filter {
	
	UsuarioService usuarioService;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		usuarioService = new UsuarioService();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		//recoger las cookies
		Cookie[] cookies = req.getCookies();
		
		//buscar la cookie usuario
		for(Cookie c : cookies) {
			if("usuario".equals(c.getName())) {
				//si existe, buscar el usuario en la BD
				Usuario u = usuarioService.getUserByUsuario(c.getValue());
				//añadir el usuario a la sesion
				req.getSession().setAttribute("usuario", u);
			}
		}

		if(req.getSession().getAttribute("usuario")!=null) {
			chain.doFilter(request, response);
		}else {
			res.sendRedirect("../login.jsp");
		}
	}
	
	@Override
	public void destroy() {}

}
