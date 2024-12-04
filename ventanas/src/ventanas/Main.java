package ventanas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {

		JFrame ventana = new JFrame("Ventana de pruebas");
		ventana.setBounds(50, 50, 400, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(null);
		
		JLabel etiqueta = new JLabel("0");
		etiqueta.setBounds(100, 50, 200, 30);
		ventana.add(etiqueta);
		
		JButton botonSumar = new JButton("+1");
		botonSumar.setBounds(50, 100, 50, 30);
		ventana.add(botonSumar);
		
		JButton botonRestar = new JButton("-1");
		botonRestar.setBounds(100, 100, 50, 30);
		ventana.add(botonRestar);
		
		botonSumar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(etiqueta.getText());
				etiqueta.setText(String.valueOf(num+1));
			}
		});
		
		botonRestar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(etiqueta.getText());
				etiqueta.setText(String.valueOf(num-1));
			}
		});
		
		ventana.setVisible(true);

	}

}
