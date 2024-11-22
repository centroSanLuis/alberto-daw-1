package Objetos;

import interfaces.Rotable;

public class Ventilador implements Rotable {
    private String tipo;

    public Ventilador(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public void rotar(int grados) {
        System.out.println("El ventilador " + tipo + " rota " + grados + " grados.");
    }
}