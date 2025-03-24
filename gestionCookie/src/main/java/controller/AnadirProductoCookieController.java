package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Producto;
import service.ProductoService;

@WebServlet("/anadirProducto")
public class AnadirProductoCookieController extends HttpServlet {
    private final ProductoService productoService = new ProductoService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productoId = request.getParameter("id");

        if (productoId != null) {
            Producto producto = productoService.obtenerProductoPorId(Integer.parseInt(productoId));

            if (producto != null) {
                Map<Integer, Integer> carrito = obtenerCarritoDesdeCookies(request);
                carrito.put(producto.getId(), carrito.getOrDefault(producto.getId(), 0) + 1);

                guardarCarritoEnCookies(response, carrito);
            }
        }
        response.sendRedirect("verCarrito");
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

    private void guardarCarritoEnCookies(HttpServletResponse response, Map<Integer, Integer> carrito) {
        StringBuilder cookieValue = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : carrito.entrySet()) {
            if (cookieValue.length() > 0) cookieValue.append("!");
            cookieValue.append(entry.getKey()).append(":").append(entry.getValue());
        }

        Cookie cookie = new Cookie("carrito", cookieValue.toString());
        cookie.setMaxAge(60 * 60 * 24); // 1 día
        response.addCookie(cookie);
    }
}
