import Objetos.Coche;
import Objetos.Drone;
import Objetos.Ventilador;
import interfaces.Movible;
import interfaces.Rotable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Coche coche = new Coche("Toyota");
        Drone drone = new Drone("DJI Phantom");
        Ventilador ventilador = new Ventilador("Industrial");

        Movible[] movibles = {coche, drone};
        System.out.println("Objetos Movibles:");
        for (Movible movible : movibles) {
            movible.mover(100);
        }

        System.out.println();

        Rotable[] rotables = {drone, ventilador};
        System.out.println("Objetos Rotables:");
        for (Rotable rotable : rotables) {
            rotable.rotar(90);
        }
    }
}