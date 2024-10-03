package operadores;

public class Main {
	
	public static void main(String[] args) {
		
		/*operadores aritmeticos*/
		// + sumar dos valores
		int n1 = 3;
		int n2 = 4;
		
		int suma = n1+n2;
		
		System.out.println(n1+n2);
		System.out.println(suma);
		
		int n3 = 6;
		float n4 = 3.5f;
		
		double suma2 = n3+n4;
		
		System.out.println(n3+n4);
		// -
		// /
		// *
		
		// % - el resto de una division
		int n5 = 6;
		int n6 = 3;
		
		System.out.println(n6%2);
		
		// ++
		System.out.println("INCREMENTO");
		int n7 = 8;
		n7 = n7 + 1;
		//System.out.println(n7++);
		
		//int n8 = n7++;
		int n8 = ++n7;
		
		//System.out.println(++n7);
		System.out.println(n8);
		System.out.println(n7);
		// --
		System.out.println("DECREMENTO");
		n7 = n7 - 1;
		n7--;
		System.out.println(n7);
		
		/*operadores de relacionales*/
		
		//==
		System.out.println(1 == 1); //true
		System.out.println(1 == 2); //false
		
		//!=
		System.out.println(1 != 1); //false
		System.out.println(1 != 2); //true
		
		//>
		System.out.println(1 > 0); //true
		System.out.println(1 > 1); //false
		System.out.println(1 > 2); //false
		
		//<
		System.out.println(1 < 0); //false
		System.out.println(1 < 1); //false
		System.out.println(1 < 2); //true
		//>=
		System.out.println(1 >= 0); //true
		System.out.println(1 >= 1); //true
		System.out.println(1 >= 2); //false
		
		//<=
		System.out.println(1 <= 0); //false
		System.out.println(1 <= 1); //true
		System.out.println(1 <= 2); //true
		
		/*operadores logicos*/
		//&&
		System.out.println((1 < 0) && (1 < 2)); //false pero solo se evalua la primera
		System.out.println((1 < 2) && (1 < 0)); //false se evaluan las 2 
		
		//||
		System.out.println((1 < 0) || (1 < 2)); //true
		
		//!
		System.out.println(!(1 <= 0)); //true
		System.out.println(!(1 <= 1)); //false
	}

}
