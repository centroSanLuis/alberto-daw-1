package calculadora.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import calculadora.enums.Operacion;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;

public class Calculadora extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNumeros;
	private JLabel lblInfo;
	private String numero = "0";
	private Operacion operacion;

	public Calculadora() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		txtNumeros = new JTextField();
		txtNumeros.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNumeros.setFont(new Font("Tahoma", Font.BOLD, 40));
		txtNumeros.setEditable(false);
		txtNumeros.setBounds(52, 36, 230, 66);
		contentPane.add(txtNumeros);
		txtNumeros.setColumns(10);
		txtNumeros.setText(numero);
		
		JButton btn7 = new JButton("7");
		btn7.setBounds(52, 113, 50, 50);
		contentPane.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.setBounds(112, 113, 50, 50);
		contentPane.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.setBounds(172, 113, 50, 50);
		contentPane.add(btn9);
		
		JButton btn4 = new JButton("4");
		btn4.setBounds(52, 174, 50, 50);
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.setBounds(112, 174, 50, 50);
		contentPane.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.setBounds(172, 174, 50, 50);
		contentPane.add(btn6);
		
		JButton btn1 = new JButton("1");
		btn1.setBounds(52, 236, 50, 50);
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.setBounds(112, 235, 50, 50);
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.setBounds(172, 235, 50, 50);
		contentPane.add(btn3);
		
		JButton btn0 = new JButton("0");
		btn0.setBounds(112, 296, 50, 50);
		contentPane.add(btn0);
		
		JButton btnDividir = new JButton("/");
		btnDividir.setBounds(232, 113, 50, 50);
		contentPane.add(btnDividir);
		
		JButton btnMultiplicar = new JButton("X");
		btnMultiplicar.setBounds(232, 174, 50, 50);
		contentPane.add(btnMultiplicar);
		
		JButton btnRestar = new JButton("-");
		btnRestar.setBounds(232, 236, 50, 50);
		contentPane.add(btnRestar);
		
		JButton btnSumar = new JButton("+");
		btnSumar.setBounds(232, 296, 50, 50);
		contentPane.add(btnSumar);
		
		JButton btnResultado = new JButton("=");
		btnResultado.setBounds(52, 359, 230, 50);
		contentPane.add(btnResultado);
		
		JButton btnBorrar = new JButton("C");
		btnBorrar.setBounds(52, 296, 50, 50);
		contentPane.add(btnBorrar);
		
		lblInfo = new JLabel("");
		lblInfo.setForeground(Color.LIGHT_GRAY);
		lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInfo.setBounds(52, 11, 230, 14);
		contentPane.add(lblInfo);
		
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("1");
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("2");
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("3");
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("4");
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("5");
			}
		});
		
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("6");
			}
		});
		
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("7");
			}
		});
		
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("8");
			}
		});
		
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("9");
			}
		});
		
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNumero("0");
			}
		});
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarNumero();
			}
		});
		
		btnSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignarOperacion(Operacion.SUMA);
			}
		});
		
		btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignarOperacion(Operacion.RESTA);
			}
		});
		
		btnDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignarOperacion(Operacion.DIVISION);
			}
		});
		
		btnMultiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				asignarOperacion(Operacion.MULTIPLCACION);
			}
		});
		
		btnResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lblInfo.setText(lblInfo.getText() + txtNumeros.getText() + "=");
				
				switch(operacion) {
				
					case SUMA -> {
						int num1 = Integer.parseInt(numero);
						int num2 = Integer.parseInt(txtNumeros.getText());
						
						int resultado = num1 + num2;
						
						txtNumeros.setText(String.valueOf(resultado));
					}
					case RESTA -> {
						int num1 = Integer.parseInt(numero);
						int num2 = Integer.parseInt(txtNumeros.getText());
						
						int resultado = num1 - num2;
						
						txtNumeros.setText(String.valueOf(resultado));
					}
					case DIVISION -> {
						int num1 = Integer.parseInt(numero);
						int num2 = Integer.parseInt(txtNumeros.getText());
						
						int resultado = num1 / num2;
						
						txtNumeros.setText(String.valueOf(resultado));
					}
					case MULTIPLCACION -> {
						int num1 = Integer.parseInt(numero);
						int num2 = Integer.parseInt(txtNumeros.getText());
						
						int resultado = num1 * num2;
						
						txtNumeros.setText(String.valueOf(resultado));
					}
				
				}
				
			}
		});
	}
	
	private void reiniciarNumero() {
		txtNumeros.setText("0");
		lblInfo.setText("");
	}
	
	private void asignarOperacion(Operacion op) {
		numero = txtNumeros.getText();
		operacion = op;
		lblInfo.setText(numero + operacion.getSimbolo());
		reiniciarNumero();
	}
	
	private void insertarNumero(String num) {
		int numero = Integer.parseInt(txtNumeros.getText());
		
		if(numero != 0) {
			txtNumeros.setText(txtNumeros.getText() + num);
		}else {
			txtNumeros.setText(num);
		}
	}
}
