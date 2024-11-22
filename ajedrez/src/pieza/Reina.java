package pieza;

public class Reina extends Pieza {
    public Reina(String color) {
        super(color);
    }

    @Override
    public void mover() {
        System.out.println("La Reina (" + color + ") se mueve en cualquier dirección tantas casillas como quiera.");
    }
}
