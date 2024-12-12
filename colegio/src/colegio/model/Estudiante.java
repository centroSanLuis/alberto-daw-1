package colegio.model;

import java.time.LocalDate;
import java.util.Date;

public class Estudiante {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private String email;
	private int genero;
	//private Date fechaNacimiento;
	private LocalDate fechaNacimiento;
	private String curso;
	private String necesidadesEspeciales;
	private boolean terminosAceptados;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getNecesidadesEspeciales() {
		return necesidadesEspeciales;
	}
	public void setNecesidadesEspeciales(String necesidadesEspeciales) {
		this.necesidadesEspeciales = necesidadesEspeciales;
	}
	public boolean isTerminosAceptados() {
		return terminosAceptados;
	}
	public void setTerminosAceptados(boolean terminosAceptados) {
		this.terminosAceptados = terminosAceptados;
	}
	
}
