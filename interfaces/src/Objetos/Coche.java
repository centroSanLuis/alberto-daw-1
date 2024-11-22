package Objetos;

import interfaces.Movible;

public class Coche implements Movible {
    private String marca;

    public Coche(String marca) {
        this.marca = marca;
    }

    @Override
    public void mover(int distancia) {
        System.out.println("El coche " + marca + " se mueve " + distancia + " metros.");
    }
}