import pieza.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Crear un array de piezas
        Pieza[] piezas = {
                new Peon("blanco"),
                new Torre("negro"),
                new Caballo("blanco"),
                new Alfil("negro"),
                new Rey("blanco"),
                new Reina("negro")
        };

        // Mostrar los movimientos de cada pieza
        for (Pieza pieza : piezas) {
            System.out.println("Pieza: " + pieza.getClass().getSimpleName());
            pieza.mover();
            System.out.println();
        }
    }
}