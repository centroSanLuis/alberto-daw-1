package pieza;

public class Alfil extends Pieza {
    public Alfil(String color) {
        super(color);
    }

    @Override
    public void mover() {
        System.out.println("El Alfil (" + color + ") se mueve en diagonal.");
    }
}
