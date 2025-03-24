package service;

import model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {
    public static final List<Producto> productos = new ArrayList<>();

    static {
        productos.add(new Producto(1, "Laptop", 750.00, 1));
        productos.add(new Producto(2, "Smartphone", 450.00, 1));
        productos.add(new Producto(3, "Auriculares", 50.00, 1));
        productos.add(new Producto(4, "Teclado Mecánico", 90.00, 1));
    }

    public Producto obtenerProductoPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p; // Retorna el producto si se encuentra
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }
}
