package herencia;

public class Profesor extends Persona{

	private String nss;
	private double sueldo;
	
	public Profesor() {}

	public Profesor(String nombre, String apellidos, String dni, String email, String numTel, int altura, String nss,
			double sueldo) {
		super(nombre, apellidos, dni, email, numTel, altura);
		this.nss = nss;
		this.sueldo = sueldo;
	}
	public String getNss() {
		return nss;
	}
	public void setNss(String nss) {
		this.nss = nss;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

}
