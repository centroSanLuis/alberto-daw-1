public class MainException {
    public static void main(String[] args) {
        System.out.println("Temporizador de 5 segundos:");

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(1000);
            }
            System.out.println("¡Tiempo terminado!");
        } catch (InterruptedException e) {
            System.out.println("El temporizador fue interrumpido.");
        }
    }
}
