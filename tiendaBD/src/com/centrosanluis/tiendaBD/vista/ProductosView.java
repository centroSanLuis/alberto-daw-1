package com.centrosanluis.tiendaBD.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.centrosanluis.tiendaBD.db.AccesoBD;
import com.centrosanluis.tiendaBD.db.ProductoBD;
import com.centrosanluis.tiendaBD.dto.ProductoDTO;

public class ProductosView extends JFrame {

	private JPanel contentPane;
	private JTable tableProductos;
	private JTextField textNombreBuscar;
	private JLabel lblNombre;
	private JTextField textNombre;
	private JButton btnGuardar;
	
	/**
	 * Create the frame.
	 */
	public ProductosView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 139, 664, 271);
		contentPane.add(scrollPane);
		
		String[] columnas = {"Nombre", "Categoria", "Precio", "Descuento (%)"};
		
		ArrayList<ProductoDTO> productos = getProductos();
		
		Object[][] datos = new Object[productos.size()][columnas.length];
		
		for(int i=0;i<productos.size();i++) {
			ProductoDTO p = productos.get(i);
			
			datos[i][0] = p.getNombre();
			datos[i][1] = p.getCategoria();
			datos[i][2] = p.getPrecio();
			datos[i][3] = p.getPorcentajeDescuento();
		}
		
		DefaultTableModel model = new DefaultTableModel(datos, columnas);
		
		tableProductos = new JTable(model);
		scrollPane.setViewportView(tableProductos);
		
		JLabel lblNombreBuscar = new JLabel("Nombre");
		lblNombreBuscar.setBounds(10, 114, 46, 14);
		contentPane.add(lblNombreBuscar);
		
		textNombreBuscar = new JTextField();
		textNombreBuscar.setBounds(66, 111, 86, 20);
		contentPane.add(textNombreBuscar);
		textNombreBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<ProductoDTO> productos = getProductosByName(textNombreBuscar.getText());
				
				model.setRowCount(0);
				
				for(ProductoDTO p: productos) {
					Object[] fila = {p.getNombre(), p.getCategoria(), p.getPrecio(), p.getPorcentajeDescuento()};
					model.addRow(fila);
				}
			}
		});
		
		btnBuscar.setBounds(162, 110, 89, 23);
		contentPane.add(btnBuscar);
		
		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(66, 8, 86, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductoDTO prod = new ProductoDTO();
				prod.setNombre(textNombre.getText());
				
				guardarProducto(prod);
				
				ArrayList<ProductoDTO> productos = getProductos();
				
				model.setRowCount(0);
				
				for(ProductoDTO p: productos) {
					Object[] fila = {p.getNombre(), p.getCategoria(), p.getPrecio(), p.getPorcentajeDescuento()};
					model.addRow(fila);
				}
			}
		});
		btnGuardar.setBounds(162, 7, 89, 23);
		contentPane.add(btnGuardar);
	}
	
	private ArrayList<ProductoDTO> getProductos() {
		ArrayList<ProductoDTO> resultado = new ArrayList<ProductoDTO>();
		
		try {
			ProductoBD produtoBD = new ProductoBD(AccesoBD.DRIVER_MYSQL, "tienda", "root", "");
			resultado = produtoBD.getProductos();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	private ArrayList<ProductoDTO> getProductosByName(String nombre){
		ArrayList<ProductoDTO> resultado = new ArrayList<ProductoDTO>();
		
		try {
			ProductoBD produtoBD = new ProductoBD(AccesoBD.DRIVER_MYSQL, "tienda", "root", "");
			resultado = produtoBD.getProductoByName(nombre);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	private void guardarProducto(ProductoDTO p) {
		ProductoBD productoBD;
		try {
			productoBD = new ProductoBD(AccesoBD.DRIVER_MYSQL, "tienda", "root", "");
			productoBD.addProducto(p);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		

	}
}
