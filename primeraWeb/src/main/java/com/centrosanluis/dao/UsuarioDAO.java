package com.centrosanluis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centrosanluis.model.Rol;
import com.centrosanluis.model.Usuario;

public class UsuarioDAO {
	
	//crear un metodo para obetener todos los usuarios
	public List<Usuario> getUsers(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT usuario, nombre, apellidos, email, telefono FROM usuarios";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Usuario u = new Usuario();
				
				u.setNombre(rs.getString("nombre"));
				u.setApellidos(rs.getString("apellidos"));
				u.setEmail(rs.getString("email"));
				u.setTelefono(rs.getString("telefono"));
				u.setUsuario(rs.getString("usuario"));
				
				usuarios.add(u);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return usuarios;
	}
	
	public Usuario login(Usuario usuario) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Usuario u = new Usuario();
		
		String sql = "SELECT u.usuario, u.nombre, u.apellidos, u.email, u.telefono, r.id, r.nombre "
				+ "FROM usuarios u inner join roles r on u.roles_id = r.id "
				+ "WHERE u.usuario = ? AND u.contrasena = ?";

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
				
				Rol rol = new Rol();
				
				rol.setId(rs.getInt("id"));
				rol.setNombre(rs.getString(7));
				
				u.setRol(rol);
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
		
		String sql = "INSERT INTO usuarios (nombre, apellidos, telefono, email, usuario, contrasena, roles_id) VALUES (?,?,?,?,?,?,?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, nuevoUsuario.getNombre());
			ps.setString(2, nuevoUsuario.getApellidos());
			ps.setString(3, nuevoUsuario.getTelefono());
			ps.setString(4, nuevoUsuario.getEmail());
			ps.setString(5, nuevoUsuario.getUsuario());
			ps.setString(6, nuevoUsuario.getContrasena());
			ps.setInt(7, nuevoUsuario.getRol().getId());
			
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
