package ejercicio7;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Introduce el numero de filas de la matriz");
		int filas = entrada.nextInt();
		
		System.out.println("Introduce el numero de columnas de la matriz");
		int columnas = entrada.nextInt();
		
		int[][] matriz = new int[filas][columnas];
		
		
		System.out.println("Introduce los numeros para la matriz");
		
		for(int fila=0;fila<matriz.length;fila++) {
			for(int col=0;col<matriz[fila].length;col++) {
				matriz[fila][col] = entrada.nextInt();
			}
		}
		
		System.out.println(matriz[0]);

		for(int fila=0;fila<matriz.length;fila++) {
			for(int col=0;col<matriz[fila].length;col++) {
				System.out.print(matriz[fila][col] + " ");
			}
			System.out.println();
		}
		
		for(int fila=0;fila<filas;fila++) {
			int sumaFila = 0;
			for(int col=0;col<columnas;col++) {
				sumaFila = sumaFila + matriz[fila][col];
			}
			System.out.println("La suma de la fila " + fila + " es " + sumaFila);
		}
		
		for(int col=0;col<columnas;col++) {
			int sumaColumna = 0;
			for(int fila=0;fila<filas;fila++) {
				sumaColumna = sumaColumna + matriz[fila][col] ;
			}
			System.out.println("La suma de la columna " + col + " es " + sumaColumna);
		}
		
		entrada.close();
		
	}
}
