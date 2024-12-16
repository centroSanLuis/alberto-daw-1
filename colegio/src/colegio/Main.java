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
		
		//AltaEstudiante window = new AltaEstudiante(estudiantes);
		
		ListadoEstudiante window = new ListadoEstudiante(estudiantes);
		
		window.setVisible(true);
		
	}

}
