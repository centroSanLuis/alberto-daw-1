package pieza;

public class Torre extends Pieza {
    public Torre(String color) {
        super(color);
    }

    @Override
    public void mover() {
        System.out.println("La Torre (" + color + ") se mueve en línea recta horizontal o vertical.");
    }
}
