package tienda;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Que tipo de cliente eres:");
		System.out.println("1.-Cliente regular");
		System.out.println("2.-Socio");
		System.out.println("3.-Cliente VIP");
		
		int tipoCliente = entrada.nextInt();
		
		if(tipoCliente > 0 && tipoCliente <=3) {
			System.out.println("Introduce el importe de la compra:");
			
			double importe = entrada.nextDouble();
			double importeFinal = 0;
			
			switch(tipoCliente) {
				case 1:
					importeFinal = importe;
					break;
				case 2:
					importeFinal = importe * 0.95;
					break;
				case 3:
					importeFinal = importe * 0.8;
					break;
			}
			
			System.out.println("El importe total a pagar es: " + importeFinal);
		}else {
			System.out.println("Tipo de usuario no valido");
		}
		

		entrada.close();
		
	}
}
