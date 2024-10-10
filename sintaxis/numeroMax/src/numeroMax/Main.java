package numeroMax;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		//int[] numeros = new int[5];
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("introduce el primer numero:");
		int num1 = entrada.nextInt();
		//numeros[0] = entrada.nextInt();
		
		System.out.println("introduce el segundo numero:");
		int num2 = entrada.nextInt();
		
		System.out.println("introduce el tercer numero:");
		int num3 = entrada.nextInt();
		
		System.out.println("introduce el cuarto numero:");
		int num4 = entrada.nextInt();
		
		System.out.println("introduce el quinto numero:");
		int num5 = entrada.nextInt();
		
		//numeros[0] = num1;
		//numeros[1] = num2;
		//numeros[2] = num3;
		//numeros[3] = num4;
		//numeros[4] = num5;
		
		int[] numeros = {num1,num2,num3,num4,num5};
		
		//[1,3,56,2,7]
		
		/*int numeroMax = 0;
		
		for(int i=0; i < numeros.length; i++) {
			if(numeros[i] > numeroMax) {
				numeroMax = numeros[i];
			}
		}*/
		
		int numeroMax = numeros[0];
		
		for(int i=1; i < numeros.length; i++) {
			if(numeros[i] > numeroMax) {
				numeroMax = numeros[i];
			}
		}
		
		System.out.println("El numero maximo es: " + numeroMax);
		
	}

}
