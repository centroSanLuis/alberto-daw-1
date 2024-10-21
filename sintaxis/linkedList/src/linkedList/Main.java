package linkedList;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		LinkedList<Integer> numeros = new LinkedList<>();
		
		numeros.add(10);
		numeros.add(20);
		numeros.add(30);
		
		numeros.addFirst(0);
		numeros.removeFirst();
		
		for(Integer num: numeros) {
			System.out.println(num);
		}
		
	}

}
