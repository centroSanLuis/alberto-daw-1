package meses;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner entrada = new Scanner(System.in);
		

		String[] meses = {"Enero", 
				"Febrero", 
				"Marzo", 
				"Abril", 
				"Mayo", 
				"Junio", 
				"Julio", 
				"Agosto",
				"Septiembre",
				"Octubre",
				"Noviembre",
				"Diciembre"};
		
		/*
		String[] meses = new String[12];
		meses[0] = "Enero";
		....
		*/
		
		
		System.out.println("Que mes quieres saber?");
		int mes = entrada.nextInt();
		
		if(mes > 0 && mes <= 12) {
			System.out.println(meses[mes-1]);
		}else {
			System.out.println("Ese mes no existe!!");
		}
		
		entrada.close();
	}

}
