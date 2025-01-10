package com.centrosanluis.tiendaBD.db;

import java.sql.SQLException;
import java.util.ArrayList;

import com.centrosanluis.tiendaBD.dto.ProductoDTO;

public class ProductoBD extends AccesoBD{

	public ProductoBD(String tipoBD, String bd, String user, String pass) throws ClassNotFoundException, SQLException {
		super(tipoBD, bd, user, pass);

	}
	
	public ArrayList<ProductoDTO> getProductos(){
		ArrayList<ProductoDTO> resultado = new ArrayList<ProductoDTO>();
		
		try {
			conectar();
			String sql = "SELECT * FROM productos";
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				ProductoDTO producto = new ProductoDTO();
				
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setCategoria(rs.getString("categoria"));
				
				if(0 == rs.getInt("descuento")) {
					producto.setDescuento(false);	
				}else {
					producto.setDescuento(true);
				}
				
				producto.setPorcentajeDescuento(rs.getInt("porcentaje"));
				producto.setPrecio(rs.getDouble("precio"));
				
				resultado.add(producto);
			}
			
			desconectar();
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	public ProductoDTO getProdutoById(int id) {
		ProductoDTO resultado = new ProductoDTO();
		
		try {
			conectar();
			String sql = "SELECT * FROM productos WHERE id = " + id;
			rs = st.executeQuery(sql);
			
			if(rs.next()) {
				resultado.setId(rs.getInt("id"));
				resultado.setNombre(rs.getString("nombre"));
				resultado.setCategoria(rs.getString("categoria"));
				
				if(0 == rs.getInt("descuento")) {
					resultado.setDescuento(false);	
				}else {
					resultado.setDescuento(true);
				}
				
				resultado.setPorcentajeDescuento(rs.getInt("porcentaje"));
				resultado.setPrecio(rs.getDouble("precio"));
			}
			
			desconectar();
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return resultado;
	}
	
	public ArrayList<ProductoDTO> getProductoByName(String nombre){
		ArrayList<ProductoDTO> resultado = new ArrayList<ProductoDTO>();
		
		try {
			conectar();
			String sql = "SELECT * FROM productos WHERE nombre like '%" + nombre + "%'";
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				ProductoDTO producto = new ProductoDTO();
				
				producto.setId(rs.getInt("id"));
				producto.setNombre(rs.getString("nombre"));
				producto.setCategoria(rs.getString("categoria"));
				
				if(0 == rs.getInt("descuento")) {
					producto.setDescuento(false);	
				}else {
					producto.setDescuento(true);
				}
				
				producto.setPorcentajeDescuento(rs.getInt("porcentaje"));
				producto.setPrecio(rs.getDouble("precio"));
				
				resultado.add(producto);
			}
			
			desconectar();
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public void addProducto(ProductoDTO producto) {
		try {
			conectar();
			String sql = "INSERT INTO productos (nombre) VALUES ('"+producto.getNombre()+"')";
			st.execute(sql);
			
			desconectar();
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
