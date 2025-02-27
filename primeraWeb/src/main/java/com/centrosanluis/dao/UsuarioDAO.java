package com.centrosanluis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.centrosanluis.model.Usuario;

public class UsuarioDAO {
	
	public Usuario login(Usuario usuario) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Usuario u = new Usuario();
		
		String sql = "SELECT usuario, nombre, apellidos, email, telefono "
				+ "FROM usuarios "
				+ "WHERE usuario = ? AND contrasena = ?";

		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, usuario.getUsuario());
			ps.setString(2, usuario.getContrasena());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				u.setNombre(rs.getString("nombre"));
				u.setApellidos(rs.getString("apellidos"));
				u.setEmail(rs.getString("email"));
				u.setTelefono(rs.getString("telefono"));
				u.setUsuario(rs.getString("usuario"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return u;
	}

	public boolean addUser(Usuario nuevoUsuario) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO usuarios (nombre, apellidos, telefono, email, usuario, contrasena) VALUES (?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, nuevoUsuario.getNombre());
			ps.setString(2, nuevoUsuario.getApellidos());
			ps.setString(3, nuevoUsuario.getTelefono());
			ps.setString(4, nuevoUsuario.getEmail());
			ps.setString(5, nuevoUsuario.getUsuario());
			ps.setString(6, nuevoUsuario.getContrasena());
			
			if(ps.executeUpdate() > 0) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		return false;
	}

}
