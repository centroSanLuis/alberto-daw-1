package factorial;

public class Main {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Intorduce un numero positivo:");
		int numero = entrada.nextInt();
		
		int resultado = 1;
		
		for(int i =1;i<=numero;i++) {
			//resultado = resultado * i;
			
			resultado *= i;
			
			System.out.println(i);
			System.out.println(numero);
		}
		
		System.out.println("El resultado es: " + resultado);
		
		//1*2*3*4*5*6*7
		//1*1=1
		//1*2=2
		//2*3=6
		//6*4=24
		//24*5=120
	}

}
