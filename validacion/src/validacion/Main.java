package validacion;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Main {
	private static JTextField txtValor;

	public static void main(String[] args) {
		JFrame ventana = new JFrame();
		ventana.setBounds(0, 0, 335, 300);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(null);
		
		txtValor = new JTextField();
		txtValor.setBounds(217, 53, 86, 20);
		ventana.getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("Introduce nu valor para validar");
		lblValor.setBounds(22, 56, 164, 14);
		ventana.getContentPane().add(lblValor);
		
		JButton btnComprobarValor = new JButton("Validar");
		btnComprobarValor.setBounds(129, 99, 89, 23);
		ventana.getContentPane().add(btnComprobarValor);
		
		JLabel lblResultado = new JLabel("");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(73, 155, 188, 14);
		ventana.getContentPane().add(lblResultado);
		
		btnComprobarValor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					String valor = txtValor.getText().replaceAll(",", "."); //85,5
					Double.parseDouble(valor); //85.5
					
					lblResultado.setText("Es un numero");
				}catch (NumberFormatException e) {
					lblResultado.setText("NO es un numero");
				}
			}
		});
		
		ventana.setVisible(true);
	}
}
