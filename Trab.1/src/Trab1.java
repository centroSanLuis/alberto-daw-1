

import javax.swing.*;


public class Trab1 {
	public static void main(String[] args) {
		/*prog  1
		 * pedir por teclado un nombre de alumno
		 * pedir por teclado 5 notas
		 * mostrar la media
		 * mostrar si el alumno aprueba el año (debe aprobar todo)*/
        
		String nombreAlumno;	
		int notaMate = 0, notaHist = 0, notaLeng = 0, notaMusic = 0, notaBiolog = 0;
	    int sumaNotas = 0;
	    boolean aprobado = true;
		
		nombreAlumno = JOptionPane.showInputDialog("Introduce el nombre del alumno");
		
		for (int i = 1; i <= 5; i++)  {

		  int notas = Integer.parseInt(JOptionPane.showInputDialog("Ingrese las notas de cada asignatura \n"  
		        + "1: Matematicas\n"
        		+ "2: Historia\n"
        		+ "3: Lengua\n"
        		+ "4: Musica\n"
        		+ "5: Biologia\n"));
		  
		  switch (i) {
          case 1:
              notaMate = notas;
              break;
          case 2:
              notaHist = notas;
              break;
          case 3:
              notaLeng = notas;
              break;
          case 4:
              notaMusic = notas;
              break;
          case 5:
              notaBiolog = notas;
              break;
      }

      if (notas < 5) {
          aprobado = false;
      }
  }
          
		sumaNotas = notaMate + notaHist + notaLeng + notaMusic + notaBiolog;
        double media = sumaNotas / 5.0;

        JOptionPane.showMessageDialog(null, "La suma de sus notas es: " + sumaNotas);
        JOptionPane.showMessageDialog(null, "La media del año es: " + media);

        if (aprobado) {
            JOptionPane.showMessageDialog(null, "Has aprobado, felicidades " + nombreAlumno);
            
        } else {
            JOptionPane.showMessageDialog(null, "Has suspendido alguna asignatura " + nombreAlumno);
        }

		
		
		
		
		
		
		
		
	}
	


}
