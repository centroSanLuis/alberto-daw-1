package pieza;

public class Rey extends Pieza {
    public Rey(String color) {
        super(color);
    }

    @Override
    public void mover() {
        System.out.println("El Rey (" + color + ") se mueve una casilla en cualquier dirección.");
    }
}
