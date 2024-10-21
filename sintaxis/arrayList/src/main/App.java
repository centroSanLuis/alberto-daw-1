package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Cancion;

public class App {

	public static void main(String[] args) {
		ArrayList<Cancion> canciones = new ArrayList<Cancion>();
		
		//Cancion c1 = new Cancion("Una", 120, "Una Una Una Una Una Una Una Una Una Una");
		
		//insercion en el arrayList
		canciones.add(new Cancion("Una", 120, "Una Una Una Una Una Una Una Una Una Una"));
		canciones.add(new Cancion("Dos", 120, "Dos Dos Dos Dos Dos Dos Dos Dos Dos Dos Dos "));
		canciones.add(new Cancion("Tres", 120, "Tres Tres Tres Tres Tres Tres Tres Tres Tres "));
		canciones.add(new Cancion("Cuatro", 120, "Cuatro Cuatro Cuatro Cuatro Cuatro Cuatro Cuatro Cuatro "));
		canciones.add(new Cancion("Cinco", 120, "Cinco Cinco Cinco Cinco Cinco Cinco Cinco Cinco Cinco Cinco Cinco "));
		
		/*
		for(int i=0;i<canciones.size();i++) {
			Cancion c = canciones.get(i);
			System.out.println(c);
		}
		
		
		//cuando quiero recorrer todos los elementos de una coleccion uso el forEach
		for(Cancion c : canciones) {
			System.out.println(c);
		}
		*/
		
		/*boolean[] reproducidas = new boolean[canciones.size()];
		int cancionesRestantes = canciones.size();
		Random random = new Random();
		
		
		while(cancionesRestantes > 0) {
			int indice = random.nextInt(canciones.size());
			
			//if(!reproducidas[indice]) {
			if(reproducidas[indice] == false) {
				System.out.println(canciones.get(indice));
				reproducidas[indice] = true;
				cancionesRestantes--;
			}
		}*/
		
		/*Collections.shuffle(canciones);

		for(int i=0;i<canciones.size();i++) {
			//0 -> 2+32
			//(5) 0 -> 4
			System.out.println(canciones.get(i));
		}*/
		
		int reproducidas = 0;
		Random random = new Random();
		
		while(reproducidas < canciones.size()) {
			Cancion c = canciones.get(random.nextInt(canciones.size()));
			
			if(c.isReproducida() == false) {
				System.out.println(c);
				c.setReproducida(true);
				reproducidas++;
			}
		}
		
	}

}
