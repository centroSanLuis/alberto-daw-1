package estructuras;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// ESTRUCTURAS CONTROL DE FLUJO

		// condicionales
		Scanner entrada = new Scanner(System.in);

		/*
		 * System.out.println("Introduce tu edad:"); int edad = entrada.nextInt();
		 */

		// edad>=18
		/*
		 * if (edad >= 18) { System.out.println("Acceso permitido"); } else {
		 * System.out.println("Acceso denegado"); }
		 */

		/*
		 * if(edad >= 16 && edad < 18) {
		 * System.out.println("Acceso permitido con tutor"); }else if(edad >= 18) {
		 * System.out.println("Acceso permitido"); }else {
		 * System.out.println("Acceso denegado"); }
		 */

		/*
		 * if (edad >= 16 && edad < 18) {
		 * System.out.println("Acceso permitido con tutor"); }
		 * 
		 * if (edad >= 18) { System.out.println("Acceso permitido"); } if (edad < 18) {
		 * System.out.println("Acceso denegado"); }
		 */

		/*
		 * if(edad < 18) { System.out.println("Acceso denegado"); }
		 */

		/*
		 * System.out.println("introduce la inicial del dia de la semana:"); String dia
		 * = entrada.next();
		 * 
		 * switch(dia) { case "l": System.out.println("Lunes"); break; //terminar un
		 * control de flujo case "m": System.out.println("Martes"); break; case "x":
		 * System.out.println("Miercoles"); break; case "j":
		 * System.out.println("Jueves"); break; case "v": System.out.println("Viernes");
		 * break; default: System.out.println("Dia no valido"); }
		 */

		// repetitivas - bucles (
		// for - cuando sabemos el numero de veces que tenemos que iterar
		// while, do..while - cuando no sabemos cuantas veces vamos a iterar)

		// for(inicializacion;condicion;cadaVuelta)
		for (int i = 0; i < 10; i++) {
			System.out.println("Mensaje " + i);
		}

		System.out.println("Introduce tu nombre: ");
		String nombre = entrada.next();

		for (int i = nombre.length() - 1; i >= 0; i--) {
			System.out.println(nombre.charAt(i));
		}

		System.out.println("*********************************");

		for (int i = 0; i < nombre.length(); i++) { // 0..n
			System.out.println(nombre.charAt(i));
		}

		int numeroOculto = (int) (Math.random() * 10); // 0 - 10

		while (true) { // 0..n
			System.out.println("introduce un numero del 0 - 10");
			int numeroUsuario = entrada.nextInt();

			if (numeroUsuario == numeroOculto) {
				System.out.println("Has acertado");
				break;
			}
		}

		// do..while 1..n
		int numeroUsuario = 0;
		
		do {
			System.out.println("introduce un numero del 0 - 10");
			numeroUsuario = entrada.nextInt();
		} while (numeroUsuario != numeroOculto);
		
		System.out.println("Has acertado");

		System.out.println("Fin del programa");

		entrada.close();
	}

}
