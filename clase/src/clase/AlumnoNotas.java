package clase;
import java.util.Scanner;

public class AlumnoNotas {
    
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese nota de Ingles");
        double notaingles = scanner.nextDouble();
        
        System.out.println("Ingrese nota de Mates");
        double notamates = scanner.nextDouble();
        
        System.out.println("Ingrese nota de Lengua");
        double notalengua = scanner.nextDouble();
        
        System.out.println("Ingrese nota de Historia");
        double notahistoria = scanner.nextDouble();
        
        System.out.println("Ingrese nota de Latin");
        double notalatin = scanner.nextDouble();
        
        double media = (notahistoria+notaingles+notalatin+notalengua+notamates)/5;
        System.out.println("La nota media del alumno es: " + media);
        
        if (media >=5 ) {
        System.out.println("apto");
        } else {
        	System.out.println("no apto");
        }

	}
	
}
        
        

