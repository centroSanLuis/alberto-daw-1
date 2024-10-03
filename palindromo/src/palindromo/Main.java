package palindromo;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		//introducir una palabra
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce una palabra");
		String palabra = entrada.nextLine();
		String palabraSinEspacios = "";
		String reversa = "";
		
		//quitar espacios para las frases
		for(int i=0;i<palabra.length();i++) {
			if(palabra.charAt(i) != ' ') {
				palabraSinEspacios = palabraSinEspacios + palabra.charAt(i);
			}
		}
		
		//dar la vuelta a esa palabra
		for(int i = palabraSinEspacios.length()-1;i>=0;i--) {
			//System.out.println(palabra.charAt(i));
			reversa = reversa + palabraSinEspacios.charAt(i);
		}
		
		//comparar la palabra y la palabra al reves

		if(palabraSinEspacios.equalsIgnoreCase(reversa)) {
			System.out.println("Es palindromo");
		}else {
			System.out.println("No es palindromo");
		}

		entrada.close();
	}

}
