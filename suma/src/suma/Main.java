package suma;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame ventana = new JFrame("Sumadora");
		ventana.setBounds(0, 0, 500, 350);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setLayout(null);
		
		JLabel lblNumero1 = new JLabel("Introduce el primer numero");
		lblNumero1.setBounds(50, 100, 200, 30);
		ventana.add(lblNumero1);
		
		JTextField txtNumero1 = new JTextField();
		txtNumero1.setBounds(255, 100, 150, 30);
		ventana.add(txtNumero1);
		
		JLabel lblNumero2 = new JLabel("Introduce el segundo numero");
		lblNumero2.setBounds(50, 150, 200, 30);
		ventana.add(lblNumero2);
		
		JTextField txtNumero2 = new JTextField();
		txtNumero2.setBounds(255, 150, 150, 30);
		ventana.add(txtNumero2);
		
		JButton btnSumar = new JButton("Sumar");
		btnSumar.setBounds(175, 200, 150, 30);
		ventana.add(btnSumar);
		
		JLabel lblResultado = new JLabel();
		lblResultado.setBounds(175, 250, 150, 30);
		ventana.add(lblResultado);
		
		btnSumar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					int num1 = Integer.parseInt(txtNumero1.getText());
					int num2 = Integer.parseInt(txtNumero2.getText());
					
					lblResultado.setText("Suma total: " + String.valueOf(num1+num2));
				}catch (NumberFormatException e) {
					lblResultado.setText("Debes introducir valores numericos");
				}catch(Exception e) {
					lblResultado.setText("Error inesperado");
				}
				
			}
			
		});
		
		ventana.setVisible(true);
	}

}
