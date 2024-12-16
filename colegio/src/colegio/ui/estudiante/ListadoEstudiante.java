package colegio.ui.estudiante;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import colegio.model.Estudiante;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ListadoEstudiante extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblAlumnos;
	private JTextField textField;

	public ListadoEstudiante(ArrayList<Estudiante> estudiantes) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//columnas de la tabla
		String[] columnas = {"DNI", "Nombre", "Apellidos", "Fecha Nacimiento", "Genero", "Email", "Curso"};
		
		//modelo de la tabla
		DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
		
		tblAlumnos = new JTable(modeloTabla);
		tblAlumnos.setBounds(0, 0, 550, 150);
		
		//añadir estudiantes al modelo
		for(Estudiante e: estudiantes) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaNac = e.getFechaNacimiento().format(formatter);
			String genero = "";
			
			switch (e.getGenero()) {
				case 0 ->{
					genero = "Femenino";
				}
				case 1 ->{
					genero = "Masculino";
				}
				case 2 ->{
					genero = "Otro";
				}
			}
			
			Object[] fila = {e.getDni(), e.getNombre(), e.getApellidos(), fechaNac, genero, e.getEmail(), e.getCurso()};
			modeloTabla.addRow(fila);
		}

		JScrollPane scrollPane = new JScrollPane(tblAlumnos);
		scrollPane.setBounds(10, 187, 550, 150);
		
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 23, 46, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(67, 20, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

	}
}
