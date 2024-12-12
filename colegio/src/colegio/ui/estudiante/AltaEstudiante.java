package colegio.ui.estudiante;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import colegio.model.Estudiante;
import java.awt.Color;

public class AltaEstudiante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textDni;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textEmail;
	private JTextField textFechaNacimiento;
	private final ButtonGroup btnGroupGenero = new ButtonGroup();

	public AltaEstudiante(ArrayList<Estudiante> estudiantes) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Alta de Estudiante");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 386, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 66, 46, 14);
		contentPane.add(lblDni);
		
		textDni = new JTextField();
		textDni.setBounds(66, 63, 109, 20);
		contentPane.add(textDni);
		textDni.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(66, 91, 109, 20);
		contentPane.add(textNombre);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 94, 46, 14);
		contentPane.add(lblNombre);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(66, 122, 109, 20);
		contentPane.add(textApellidos);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 125, 46, 14);
		contentPane.add(lblApellidos);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(66, 153, 109, 20);
		contentPane.add(textEmail);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 156, 46, 14);
		contentPane.add(lblEmail);
		
		JRadioButton rdbtnFemenino = new JRadioButton("Femenino");
		btnGroupGenero.add(rdbtnFemenino);
		rdbtnFemenino.setBounds(10, 213, 109, 23);
		contentPane.add(rdbtnFemenino);
		
		JRadioButton rdbtnMasculino = new JRadioButton("Masculino");
		btnGroupGenero.add(rdbtnMasculino);
		rdbtnMasculino.setBounds(121, 213, 109, 23);
		contentPane.add(rdbtnMasculino);
		
		JRadioButton rdbtnOtro = new JRadioButton("Otro");
		btnGroupGenero.add(rdbtnOtro);
		rdbtnOtro.setBounds(232, 213, 109, 23);
		contentPane.add(rdbtnOtro);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setBounds(14, 193, 46, 14);
		contentPane.add(lblGenero);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha Nacimiento");
		lblFechaNacimiento.setBounds(201, 66, 86, 14);
		contentPane.add(lblFechaNacimiento);
		
		textFechaNacimiento = new JTextField();
		textFechaNacimiento.setBounds(297, 63, 127, 20);
		contentPane.add(textFechaNacimiento);
		textFechaNacimiento.setColumns(10);
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setBounds(201, 94, 46, 14);
		contentPane.add(lblCurso);
		
		JComboBox<String> cmbCurso = new JComboBox<String>();
		cmbCurso.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecciona un Curso", "DAW", "Integracion Social"}));
		cmbCurso.setBounds(297, 90, 127, 22);
		contentPane.add(cmbCurso);
		
		JTextArea textAreaNecesidades = new JTextArea();
		textAreaNecesidades.setBounds(14, 268, 410, 91);
		contentPane.add(textAreaNecesidades);
		
		JCheckBox chckbxTerminos = new JCheckBox("Acepto los terminos de uso");
		chckbxTerminos.setBounds(273, 366, 155, 23);
		contentPane.add(chckbxTerminos);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.setBounds(10, 448, 414, 23);
		contentPane.add(btnAlta);
		
		JLabel lblNecesidades = new JLabel("Necesidades Especiales");
		lblNecesidades.setBounds(10, 243, 123, 14);
		contentPane.add(lblNecesidades);
		
		JLabel lblError = new JLabel("Tienes errores en tu formulario");
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(10, 414, 414, 23);
		lblError.setVisible(false);
		contentPane.add(lblError);
		
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dni = textDni.getText();
				String nombre = textNombre.getText();
				String apellidos = textApellidos.getText();
				String curso = cmbCurso.getSelectedItem().toString();
				String email = textEmail.getText();
				String fechaNacimiento = textFechaNacimiento.getText();
				String necesidades = textAreaNecesidades.getText();
				boolean terminos = chckbxTerminos.isSelected();
				
				JRadioButton radioSeleccionado;
				String genero = "";
				
				Enumeration<AbstractButton> buttons = btnGroupGenero.getElements();
				
		        while (buttons.hasMoreElements()) {
		        	AbstractButton button = buttons.nextElement();
		        	
		            if (button.isSelected()) {
		            	radioSeleccionado = (JRadioButton) button;	
		            	genero = radioSeleccionado.getText();
		            	break;
		            }
		        }
		        
		        if(validarCampos(dni, nombre, apellidos, curso, email, fechaNacimiento, terminos, genero)) {
			        lblError.setVisible(false);
		        	Estudiante estudiante = new Estudiante();
			        
			        estudiante.setApellidos(apellidos);
			        estudiante.setCurso(curso);
			        estudiante.setDni(dni);
			        estudiante.setEmail(email);
			        estudiante.setNecesidadesEspeciales(necesidades);
			        estudiante.setNombre(nombre);
			        estudiante.setTerminosAceptados(terminos);
			        
			        //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			        //DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("dd/MM/yyyy").toFormatter();
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			        
					estudiante.setFechaNacimiento(LocalDate.parse(fechaNacimiento, formatter));

			        switch (genero) {
				        case "Femenino" -> {
				        	estudiante.setGenero(0);
				        }
				        case "Masculino" -> {
				        	estudiante.setGenero(1);
				        }
				        case "Otro" -> {
				        	estudiante.setGenero(2);
				        }
			        }
			        
					estudiantes.add(estudiante);
		        }else {
		        	lblError.setVisible(true);
		        }
		        

			}
		});
	}
	
	private boolean validarCampos(String dni, String nombre, String apellidos, String curso, String email, String fechaNacimiento, boolean terminos, String genero) {
		if(dni.equals("") || dni.length() != 9) { //no este repetido, que el dni tenga formato correcto
			return false;
		}

		if(nombre.equals("")) {
			return false;
		}
		
		if(apellidos.equals("")) {
			return false;
		}
		
		if(curso.equals("Selecciona un Curso")) {
			return false;
		}
		
		if(email.equals("")) { //comprobar que el email sea correcto
			return false;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			format.parse(fechaNacimiento);
		} catch (ParseException e) {
			return false;
		}
		
		if(!terminos) {
			return false;
		}
		
		if(genero.equals("")) {
			return false;
		}
		
		return true;
	}
}
