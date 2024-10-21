package atencionLinkedList.app;

import java.util.LinkedList;
import java.util.Scanner;

import atencionLinkedList.model.Cliente;

public class App {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		boolean atenderClientes = true;
		LinkedList<Cliente> colaClientes = new LinkedList<>();
		
		
		colaClientes.add(new Cliente("Ibai", "Gorospe"));
		colaClientes.add(new Cliente("Sergio", "Gris"));
		colaClientes.add(new Cliente("Jon", "B"));

		while(atenderClientes && colaClientes.size() > 0) {
			Cliente clienteAtendido = colaClientes.removeFirst();
			
			System.out.println("El cliente " + clienteAtendido + " esta atendido");
			
			System.out.println("¿Quieres seguir atendido clientes?(s/n)");
			String respuesta = entrada.nextLine();
			
			if(!"s".equals(respuesta)) {
				atenderClientes = false;
			}
		}
		
		entrada.close();
	}

}
