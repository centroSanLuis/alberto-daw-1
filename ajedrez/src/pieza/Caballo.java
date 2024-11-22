package pieza;

public class Caballo extends Pieza {
    public Caballo(String color) {
        super(color);
    }

    @Override
    public void mover() {
        System.out.println("El Caballo (" + color + ") se mueve en forma de 'L'.");
    }
}
