package ejercicio2;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		
		String[] asignaturas = {"Historia", 
				"Matematicas", 
				"Fisica", 
				"Euskera", 
				"Lengua", 
				"Tecnologia",
				"Filosofia",
				"Gimnasia",
				"Ingles",
				"Biologia"};
		Double[] notas = new Double[asignaturas.length];
		
		double suma = 0;
		
		for(int i=0;i<asignaturas.length;i++) {
			System.out.println("Introduce una nota para la asignatura " + asignaturas[i] + ":");
			notas[i] = entrada.nextDouble();
			suma = suma + notas[i];
		}
		
		for(int i=0; i<asignaturas.length; i++) {
			System.out.println("La nota de " + asignaturas[i] + "es " + notas[i]);
		}
		
		double media = suma/notas.length;
		System.out.println("La media es: " + media);
		
		entrada.close();
	}

}
