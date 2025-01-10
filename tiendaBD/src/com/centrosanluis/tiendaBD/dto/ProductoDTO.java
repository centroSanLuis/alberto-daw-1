package com.centrosanluis.tiendaBD.dto;

public class ProductoDTO {
	
	private int id;
	private String nombre, categoria;
	private double precio;
	private boolean descuento;
	private int porcentajeDescuento;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public boolean isDescuento() {
		return descuento;
	}
	public void setDescuento(boolean descuento) {
		this.descuento = descuento;
	}
	public int getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	public void setPorcentajeDescuento(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	@Override
	public String toString() {
		return "ProductoDAO [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio
				+ ", descuento=" + descuento + ", porcentajeDescuento=" + porcentajeDescuento + "]";
	}
}
