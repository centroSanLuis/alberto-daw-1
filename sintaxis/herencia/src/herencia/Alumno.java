package herencia;

public class Alumno extends Persona{
	
	private String numAlumno;

	public Alumno() {}
	
	public Alumno(String nombre, 
			String apellidos, 
			String dni, 
			String email, 
			String numTel, 
			int altura,
			String numAlumno) {
		super(nombre, apellidos, dni, email, numTel, altura);
		
		this.numAlumno = numAlumno;
	}

	public String getNumAlumno() {
		return numAlumno;
	}

	public void setNumAlumno(String numAlumno) {
		this.numAlumno = numAlumno;
	}
	
	public void saludar() {
		super.saludar();
		System.out.println(" Hola Profe!");
	}
	
	@Override
	public String toString() {
		return this.getNombre() + " " + this.getApellidos();
	}

}
