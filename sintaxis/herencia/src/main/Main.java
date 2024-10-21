package main;

import herencia.Alumno;
import herencia.Persona;
import herencia.Profesor;

public class Main {
	
	public static void main (String[] args) {
		Persona p = new Persona();
		Alumno a = new Alumno();
		Profesor pro = new Profesor();
		
		a.setNombre("Alberto");
		a.setApellidos("GDC");
		
		System.out.println(a);
		
		//p.saludar();
		a.saludar();
		//pro.saludar();
	}

}
