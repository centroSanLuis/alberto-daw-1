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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class ListadoEstudiante extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tblAlumnos;
	private JTextField textNombre;
	private JTextField textDni;
	private JTextField textApellidos;

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
		
		//Creamos el modelo de seleccion y forzamos a que solo se pueda seleccionar una fila a la vez
		ListSelectionModel modeloSeleccion = tblAlumnos.getSelectionModel();
		modeloSeleccion.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
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
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 59, 46, 14);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(67, 56, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textDni = new JTextField();
		textDni.setColumns(10);
		textDni.setBounds(67, 28, 86, 20);
		contentPane.add(textDni);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 31, 46, 14);
		contentPane.add(lblDni);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(67, 84, 86, 20);
		contentPane.add(textApellidos);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 87, 46, 14);
		contentPane.add(lblApellidos);
		
		modeloSeleccion.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int filaSeleccionada = tblAlumnos.getSelectedRow();
				
				textDni.setText(tblAlumnos.getValueAt(filaSeleccionada, 0).toString());
				textNombre.setText(tblAlumnos.getValueAt(filaSeleccionada, 1).toString());
				
			}
		});

	}
}
