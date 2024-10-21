package model;

public class Cancion {
	
	private String titulo;
	private int duracion;
	private String letra;
	private Autor autor;
	private boolean reproducida;
	
	public Cancion(String titulo, int duracion, String letra) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.letra = letra;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
	public boolean isReproducida() {
		return reproducida;
	}

	public void setReproducida(boolean reproducida) {
		this.reproducida = reproducida;
	}

	@Override
	public String toString() {
		return titulo + " (" + duracion + ") " + "- " + letra; 
	}
}
