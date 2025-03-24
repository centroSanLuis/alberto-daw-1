<%@ page import="java.util.List" %>
<%@ page import="model.Producto" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tienda Online</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h1>Lista de Productos</h1>
    
    <table border="1">
        <tr>
            <th>Producto</th>
            <th>Precio</th>
            <th>Acción</th>
        </tr>
        <% 
            List<Producto> productos = (List<Producto>) application.getAttribute("productos");
            if (productos == null) {
                response.sendRedirect("cargarProductos");
            } else {
                for (Producto producto : productos) { 
        %>
        <tr>
            <td><%= producto.getNombre() %></td>
            <td><%= producto.getPrecio() %>€</td>
            <td>
                <form action="anadirProducto" method="post">
                    <input type="hidden" name="id" value="<%= producto.getId() %>">
                    <button type="submit">Añadir al Carrito</button>
                </form>
            </td>
        </tr>
        <% 
                } 
            }
        %>
    </table>

    <a href="verCarrito">Ver Carrito</a>
</body>
</html>
