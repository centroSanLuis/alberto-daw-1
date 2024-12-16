package colegio;

import java.time.LocalDate;
import java.util.ArrayList;

import colegio.model.Estudiante;
import colegio.ui.estudiante.AltaEstudiante;
import colegio.ui.estudiante.ListadoEstudiante;

public class Main {
	
	public static void main(String[] args) {
		
		ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
		
		/** TODO
		 * añadir estudiantes al arraylist (new Estudiante....)
		 */
		
		Estudiante e = new Estudiante();
		
		e.setApellidos("Garcia de Cortazar");
		e.setCurso("DAW");
		e.setDni("00000001V");
		e.setEmail("acortazar@centrosanluis.com");
		e.setFechaNacimiento(LocalDate.now());
		e.setGenero(1);
		e.setNecesidadesEspeciales("muchas");
		e.setNombre("Alberto");
		e.setTerminosAceptados(true);
		
		estudiantes.add(e);
		
		Estudiante e1 = new Estudiante();
		
		e1.setApellidos("1Garcia de Cortazar");
		e1.setCurso("1DAW");
		e1.setDni("100000001V");
		e1.setEmail("1acortazar@centrosanluis.com");
		e1.setFechaNacimiento(LocalDate.now());
		e1.setGenero(0);
		e1.setNecesidadesEspeciales("1muchas");
		e1.setNombre("1Alberto");
		e1.setTerminosAceptados(true);
		
		estudiantes.add(e1);
		
		//AltaEstudiante window = new AltaEstudiante(estudiantes);
		
		ListadoEstudiante window = new ListadoEstudiante(estudiantes);
		
		window.setVisible(true);
		
	}

}
