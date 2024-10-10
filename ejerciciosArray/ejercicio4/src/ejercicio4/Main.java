package ejercicio4;

import java.util.Scanner;

public class Main {
	
	public static void main (String[] args) {
		Scanner entrada = new Scanner(System.in);
		int[] numeros = new int[8];
		
		for(int i=0;i<numeros.length;i++) {
			numeros[i] = (int) ((Math.random()*10)+1);
		}
		
		System.out.println("Introduce un numero a ver si tienes suerte:");
		int numero = entrada.nextInt();
		
		boolean encontrado = false;
		int posicion = 0;
		
		for(int i=0;i<numeros.length;i++) {
			if(numeros[i]==numero) {
				encontrado = true;
				posicion =  i;
				break;
			}
		}
		
		if(encontrado) {
			System.out.println("El numero esta en el array, en la posicion " + posicion);
		}else {
			System.out.println("El numero no esta en el array");
		}
		
		entrada.close();
	}

}
