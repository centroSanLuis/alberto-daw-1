<%@ page import="java.util.List" %>
<%@ page import="model.Producto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito de Compras</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Carrito de Compras</h2>
    <table border="1">
        <tr>
            <th>Producto</th>
            <th>Precio</th>
            <th>Cantidad</th>
        </tr>
        <% 
            List<Producto> productos = (List<Producto>) request.getAttribute("productos");
            if (productos != null && !productos.isEmpty()) {
                for (Producto producto : productos) { 
        %>
        <tr>
            <td><%= producto.getNombre() %></td>
            <td><%= producto.getPrecio() %>€</td>
            <td><%= producto.getCantidad() %></td>
        </tr>
        <% 
                } 
            } else { 
        %>
        <tr>
            <td colspan="3">El carrito está vacío.</td>
        </tr>
        <% 
            } 
        %>
    </table>
    <br>
    <a href="index.jsp">Seguir Comprando</a> | <a href="vaciarCarrito">Vaciar Carrito</a>
</body>
</html>