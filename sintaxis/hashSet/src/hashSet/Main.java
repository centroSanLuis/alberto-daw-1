package hashSet;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		HashSet<String> dnis = new HashSet<String>();
		
		dnis.add("123456789A");
		dnis.add("987654321B");
		dnis.add("123456789A");
		
		System.out.println("Los DNI de los clientes son:");
		for(String dni: dnis) {
			System.out.println(dni);
		}
	}

}
