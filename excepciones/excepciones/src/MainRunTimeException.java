import java.util.InputMismatchException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class MainRunTimeException {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //RunTimeExceptions - el compilador no las marca como error

        try {
            System.out.println("Introduzca el primer numero: ");
            int num1 = sc.nextInt();
            System.out.println("Introduzca el segundo numero: ");
            int num2 = sc.nextInt();

            System.out.println(num1 / num2);
        } catch (ArithmeticException e) {
            System.out.println("No se puede dividir entre 0");
        } catch(InputMismatchException e){
            System.out.println("Has introducido un valor no numerico");
        }finally{
            sc.close();
        }

        System.out.println("FIN DEL PROGRAMA");

    }
}