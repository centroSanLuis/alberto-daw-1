package repaso;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("¿De cuantas posiciones quieres el array?");
		int posiciones = entrada.nextInt();
		
		System.out.println("¿Como quieres inicializar el array?");
		System.out.println("1.- Con ceros");
		System.out.println("2.- Con numeros aleatorios (1 al 100)");
		System.out.println("3.- Insertando los numeros manualmente");
		
		int opcion = entrada.nextInt();
		int[] numeros = new int[posiciones];
		
		switch(opcion) {
			case 1:
				mostrarArray(numeros);
				break;
			case 2:
				Random random = new Random();
				
				for(int i=0;i<numeros.length;i++) {
					numeros[i] = random.nextInt(100) + 1;
				}
				mostrarArray(numeros);
				break;
			case 3:
				for(int i=0;i<numeros.length;i++) {
					System.out.println("Introduce un numero para la posicion " + i);
					
					int num = entrada.nextInt();
					numeros[i] = num;
					
					//numeros[i] = entrada.nextInt();
				}
				
				mostrarArray(numeros);
				break;
			default:
				System.out.println("Las opciones son entre 1 y 3, Gañan!");
		}
		
		int opcionArray;
		
		do {
			System.out.println("¿Que quieres hacer con tu array?");
			System.out.println("1.- Cambiar valores de todo el array");
			System.out.println("2.- Cambiar un valor del array");
			System.out.println("3.- Reiniciar el array");
			System.out.println("4.- Mostrar el array");
			System.out.println("5.- Suma");
			System.out.println("6.- Multiplicacion");
			System.out.println("7.- Media");
			System.out.println("8.- Factorial de cada Valor");
			System.out.println("9.- Ordenar de  menor a mayor");
			System.out.println("10.- Ordenar de mayor a menor");
			System.out.println("0.- Salir del programa");
			
			opcionArray = entrada.nextInt();
			
			switch(opcionArray) {
				case 1:
					for(int i=0;i<numeros.length;i++) {
						System.out.println("Introduce un numero para la posicion " + i);
						
						int num = entrada.nextInt();
						numeros[i] = num;
						
						//numeros[i] = entrada.nextInt();
					}
					break;
				case 2:
					System.out.println("¿Que indice quieres cambiar?");
					int pos = entrada.nextInt();
					System.out.println("¿Cual es el nuevo numero?");
					int num = entrada.nextInt();
					
					numeros[pos] = num;
					break;
				case 3:
					//numeros = new int[posiciones];
					entrada.nextLine();
					System.out.println("Se van a borrar todos los datos del Array, ¿estas seguro?(s/n)");
					String respuesta = entrada.nextLine();
					if("s".equals(respuesta)) {
						for(int i=0;i<numeros.length;i++) {
							numeros[i] = 0;
						}
					}
					
					break;
				case 4:
					mostrarArray(numeros);
					break;
				case 5:
					int resultadoSuma = 0;
					for(int i=0;i<numeros.length;i++) {
						resultadoSuma = resultadoSuma + numeros[i];
						//resultadoSuma += numeros[i];
					}
					System.out.println("La suma de todos los elementos es: " + resultadoSuma);
					break;
				case 6:
					int resultadoMulti = 1;
					for(int i=0;i<numeros.length;i++) {
						resultadoMulti = resultadoMulti * numeros[i];
					}
					System.out.println("La multiplicacion de todos los elementos es: " + resultadoMulti);
					break;
				case 7:
					int resultadoSumaMedia = 0;
					for(int i=0;i<numeros.length;i++) {
						resultadoSumaMedia = resultadoSumaMedia + numeros[i];
						//resultadoSuma += numeros[i];
					}
					
					double media = resultadoSumaMedia/numeros.length;
					
					System.out.println("La media de los numeros es " + media);
					break;
				case 8:
					for(int i=0;i<numeros.length;i++) {
						int numero = numeros[i];
						
						double resultadoFactorial = 1;
						for(int j=1;j<=numero;j++) {
							resultadoFactorial = resultadoFactorial*j;
						}
						
						System.out.println("El resultado del factorial de " + numero + " es: " + resultadoFactorial);
					}
					break;
				case 9:
					Arrays.sort(numeros);
					break;
				case 10:
					//Arrays.sort(numeros, Collections.reverseOrder());
					break;
				default:
					if(opcionArray != 0) {
						System.out.println("Opcion Incorrecta");
					}
			}
			
		}while(opcionArray!=0);
		
		System.out.println("Agur!");
		
		//System.out.println(Arrays.toString(numeros));
		
		entrada.close();
	}

	private static void mostrarArray(int[] numeros) {
		System.out.print("[");
		for(int i=0;i<numeros.length;i++) {
			System.out.print(numeros[i]);
			if(i<numeros.length-1) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
		System.out.println();
	}

}
