package Objetos;

import interfaces.Movible;
import interfaces.Rotable;

public class Drone implements Movible, Rotable {
    private String modelo;

    public Drone(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public void mover(int distancia) {
        System.out.println("El drone " + modelo + " vuela " + distancia + " metros.");
    }

    @Override
    public void rotar(int grados) {
        System.out.println("El drone " + modelo + " rota " + grados + " grados.");
    }
}
