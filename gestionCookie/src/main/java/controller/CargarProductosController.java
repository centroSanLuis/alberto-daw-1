package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import service.ProductoService;

@WebServlet("/cargarProductos")
public class CargarProductosController extends HttpServlet {

    @Override
    public void init() throws ServletException {
        List<Producto> productos = ProductoService.productos;

        // Guardar la lista en el contexto de la aplicación
        ServletContext context = getServletContext();
        context.setAttribute("productos", productos);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
