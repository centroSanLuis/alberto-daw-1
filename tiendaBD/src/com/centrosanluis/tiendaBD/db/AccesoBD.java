package com.centrosanluis.tiendaBD.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccesoBD {
	public static final String DRIVER_MYSQL = "com.mysql.jdbc.Driver";
	public static final String DRIVER_GENERICO = "sun.jdbc.odbc.JdbcOdbc.Driver";
	public static final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	
	//vars conexion
	String bd,user,pass;
	String tipoBD;
	public AccesoBD(String tipoBD, String bd, String user, String pass) throws ClassNotFoundException, SQLException {
		super();
		this.bd = bd;
		this.user = user;
		this.pass = pass;
		this.tipoBD=tipoBD;
	}
	
	public void conectar() throws ClassNotFoundException, SQLException {
		//CARGAR EL DRIVER
		Class.forName(tipoBD);
		
		//CREAR CONEXION
		//URL MySQL -> jdbc:mysql://IP:PUERTO/NOMBRE_BD
		//USER -> root
		//PASS -> NO TIENE
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd, user, pass);
		
		System.out.println("Conexión establecida");
		
		//CREAR statement -> Lo que utilizaremos para ejecutar SQL en la BD desde JAVA
		st = con.createStatement();
		
	}
	
	public void desconectar() throws SQLException {
		if(rs!=null) {
			rs.close();
		}
		st.close();
		con.close();
	}
	
	
	
}
