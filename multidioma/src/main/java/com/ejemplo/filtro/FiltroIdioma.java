package com.ejemplo.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class FiltroIdioma implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        String langParam = req.getParameter("lang");
        if (langParam != null && (langParam.equals("es") || langParam.equals("en"))) {
            session.setAttribute("lang", langParam);
        } else if (session.getAttribute("lang") == null) {
            session.setAttribute("lang", "es");
        }

        chain.doFilter(request, response);
    }

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
