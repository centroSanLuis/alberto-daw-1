package colorines;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class Main {

	public static void main(String[] args) {
		
		JFrame ventana = new JFrame();
		ventana.setBounds(0, 0, 391, 139);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		JButton btnAzul = new JButton("Azul");
		btnAzul.setBounds(50, 34, 89, 23);
		ventana.getContentPane().add(btnAzul);
		
		JButton btnRojo = new JButton("Rojo");
		btnRojo.setBounds(149, 34, 89, 23);
		ventana.getContentPane().add(btnRojo);
		
		JButton btnVerde = new JButton("Verde");
		btnVerde.setBounds(248, 34, 89, 23);
		ventana.getContentPane().add(btnVerde);
		
		btnAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.getContentPane().setBackground(Color.BLUE);
				
				//ventana.setBackground(new Color(0, 0, 255));
			}
		});
		
		btnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.getContentPane().setBackground(Color.RED);
			}
		});
		
		btnVerde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.getContentPane().setBackground(Color.GREEN);
			}
		});
		
		ventana.setVisible(true);

	}
}
