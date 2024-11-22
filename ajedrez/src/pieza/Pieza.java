package pieza;

public abstract class Pieza {
    protected String color;

    public Pieza(String color) {
        this.color = color;
    }

    public abstract void mover();

    public String getColor() {
        return color;
    }
}
