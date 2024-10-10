package cliente;

import java.util.Scanner;

public class Cliente {

	public static void Cliente(String[] args) {
		Scanner scanner = new Scanner (System.in);
		
		System.out.println("Introduzca el tipo de cliente -->");
		String tipousuario = scanner.next();
		
		System.out.println("Introduzca el importe de compra -->");
		double importeCompra = scanner.nextDouble();
		
		switch ( tipousuario) {
		case "Normal":
			System.out.println(importeCompra);
			break;
		
		case "Vip":
			importeCompra = importeCompra*80/100;
			System.out.println(importeCompra);
		break;
		
		
		case "Socio":
			importeCompra = importeCompra*95/100;
			System.out.println(importeCompra);
		break;
		
			
		
		}
		
		
		
		
		
	}
}
