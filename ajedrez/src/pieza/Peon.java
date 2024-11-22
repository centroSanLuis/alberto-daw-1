package pieza;

public class Peon extends Pieza {
    public Peon(String color) {
        super(color);
    }

    @Override
    public void mover() {
        System.out.println("El Peón (" + color + ") se mueve hacia adelante una casilla.");
    }
}
