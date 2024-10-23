package juegoDeLaVida;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static final int ALEATORIO = 1;
    public static final int NO_ALEATORIO = 2;

    public static void main (String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("*** BASES DEL JUEGO ***");

        System.out.println("Quieres que el juego comience de manera aleatoria:");
        System.out.println("1.-Si");
        System.out.println("2.-No");
        int aleatorio = entrada.nextInt();

        System.out.println("Introduce el numero de generaciones (siendo 0 infinitas)");
        int generaciones = entrada.nextInt();

        System.out.println("Introduce el numero de filas que quieres que tenga el tablero");
        int filas = entrada.nextInt();

        System.out.println("Introduce el numero de columnas que quieres que tenga el tablero");
        int columnas = entrada.nextInt();

        int[][] tableroInicial = new int[filas][columnas];

        if(ALEATORIO == aleatorio) {
            Random random = new Random();
            for(int fila=0;fila<tableroInicial.length;fila++) {
                for(int col=0;col<tableroInicial[fila].length;col++) {
                    tableroInicial[fila][col] = random.nextInt(2);
                }
            }

        }else {
            for(int fila=0;fila<tableroInicial.length;fila++) {
                for(int col=0;col<tableroInicial[fila].length;col++) {
                    System.out.println("Introduce un valor para la posicion ["+fila+","+col+"]");
                    tableroInicial[fila][col] = entrada.nextInt();
                }
            }
        }


        int generacion = 0;
        entrada.nextLine();
        while((generaciones==0) || (generacion <= generaciones)) {
            int[][] tableroSiguienteGen = new int[filas][columnas];
            mostrarTablero(tableroInicial, generacion);

            for(int fila=0;fila<tableroInicial.length;fila++) {
                for(int col=0;col<tableroInicial[fila].length;col++) {
                    int vecinos = contarVecinos(tableroInicial,fila,col);

                    if((tableroInicial[fila][col] == 0) && vecinos == 3) {
                        tableroSiguienteGen[fila][col] = 1;
                    }else if((tableroInicial[fila][col] == 1) && (vecinos > 3 || vecinos <= 1)) {
                        tableroSiguienteGen[fila][col] = 0;
                    }else {
                        tableroSiguienteGen[fila][col] = tableroInicial[fila][col];
                    }
                }
            }

            tableroInicial = tableroSiguienteGen;

            generacion++;

            if(generaciones==0 && generacion%100 == 0){
                System.out.println("¿Quieres contibuar con otras 100 generaciones (s/n)?");
                String respuesta = entrada.nextLine();
                if(!respuesta.equals("s")) {
                    break;
                }
            }
        }


        entrada.close();
    }

    public static int contarVecinos(int[][] tablero, int fila, int columna) {
        int vecinos = 0;

//si la fila es 0 no puede hacerse -1, si la fila es fila.length-1 no puede ser +1
        int filaMin = 0;
        int filaMax = tablero.length -1;

        if(fila > 0) {
            filaMin = fila - 1;
        }

        if(fila < tablero.length -1) {
            filaMax = fila + 1;
        }

        int colMin = 0;
        int colMax = tablero[0].length -1;

        if(columna > 0) {
            colMin = columna - 1;
        }

        if(columna < tablero[fila].length -1) {
            colMax = columna + 1;
        }

        for(int i=filaMin;i<=filaMax;i++) {
            for(int j=colMin;j<=colMax;j++) {
                if(tablero[i][j] == 1) {
                    vecinos++;
                }
            }
        }

        if(tablero[fila][columna] == 1) {
            vecinos--;
        }
        return vecinos;
    }

    public static void mostrarTablero(int[][] tablero, int generacion) {
        int populacion = obtenerPopulacion(tablero);
        System.out.println(" --- Generacion " + generacion + " (" + populacion + ") --- ");
        for(int fila=0;fila<tablero.length;fila++) {
            for(int col=0;col<tablero[fila].length;col++) {
                System.out.print(tablero[fila][col] + " ");
            }
            System.out.println();
        }
    }

    private static int obtenerPopulacion(int[][] tablero) {
        int populacion = 0;
        for(int fila=0;fila<tablero.length;fila++) {
            for(int col=0;col<tablero[fila].length;col++) {
                populacion += tablero[fila][col];
            }
        }
        return populacion;
    }

}