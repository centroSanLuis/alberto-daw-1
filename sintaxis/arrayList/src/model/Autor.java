package model;

public class Autor {
	
	private String nombre;
	private String apellidos;
	private String pais;
	private String biografia;
	
	public Autor(String nombre, String apellidos, String pais, String biografia) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.pais = pais;
		this.biografia = biografia;
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
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	

}
