package com.centrosanluis.model;

public class Usuario {
	
	private String nombre, apellidos, email, telefono, usuario, contrasena;
	private Rol rol;

	public Usuario() {
		super();
	}
	
	public Usuario(String nombre, String apellidos, String email, String telefono, String usuario,
			String contrasena, Rol rol) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.telefono = telefono;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.rol = rol;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", telefono=" + telefono
				+ ", usuario=" + usuario + ", contrasena=" + contrasena + ", rol=" + rol + "]";
	}

}
