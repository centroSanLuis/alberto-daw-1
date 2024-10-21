package herencia;

public class Persona {
	
	private String nombre;
	private String apellidos;
	private String dni;
	private String email;
	private String numTel;
	private int altura;
	
	/*public Persona() {}
	
	public Persona(String dni, String email) {
		
	}
	
	public Persona(String nombre, int altura) {
		
	}
	
	public Persona(int altura, String apellido) {
		
	}*/
	public Persona() {}
	
	public Persona(String nombre, String apellidos, String dni, String email, String numTel, int altura) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.email = email;
		this.numTel = numTel;
		this.altura = altura;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	
	public void saludar() {
		System.out.print("Buenos dias!");
	}

}
