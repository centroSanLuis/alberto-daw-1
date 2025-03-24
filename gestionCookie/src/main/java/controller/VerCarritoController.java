package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import service.ProductoService;

@WebServlet("/verCarrito")
public class VerCarritoController extends HttpServlet {
    private final ProductoService productoService = new ProductoService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<Integer, Integer> carrito = obtenerCarritoDesdeCookies(request);
        List<Producto> productosEnCarrito = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : carrito.entrySet()) {
            Producto producto = productoService.obtenerProductoPorId(entry.getKey());
            if (producto != null) {
                producto.setCantidad(entry.getValue());
                productosEnCarrito.add(producto);
            }
        }

        request.setAttribute("productos", productosEnCarrito);
        request.getRequestDispatcher("carrito.jsp").forward(request, response);
    }

    private Map<Integer, Integer> obtenerCarritoDesdeCookies(HttpServletRequest request) {
        Map<Integer, Integer> carrito = new HashMap<>();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("carrito".equals(cookie.getName())) {
                    String[] items = cookie.getValue().split("!");
                    for (String item : items) {
                        String[] datos = item.split(":");
                        carrito.put(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
                    }
                }
            }
        }
        return carrito;
    }
}
