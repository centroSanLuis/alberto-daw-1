package com.centrosanluis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centrosanluis.model.Rol;

public class RolDAO {

	//public Rol getRolById(int id)
	public Rol getRolById(int id) {
		Rol rol = new Rol();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, nombre FROM roles WHERE id = ?";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				rol = new Rol(rs.getInt("id"), rs.getString("nombre"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return rol;
	}	
	
	public List<Rol> getRoles() {
		List<Rol> roles = new ArrayList<Rol>();
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT id, nombre FROM roles";
		
		try {
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Rol rol = new Rol(rs.getInt("id"), rs.getString("nombre"));
				
				roles.add(rol);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			AccesoBD.closeConnection(null, ps, con);
		}
		
		return roles;
	}

	//createOrUpdateRol
	public boolean createOrUpdateRol(Rol rol) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "";
		
		if(rol.getId() != 0) {
			sql = "UPDATE roles SET nombre = ? WHERE id = ?";
		}else {
			sql = "INSERT INTO roles (nombre) VALUES (?)";
		}
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, rol.getNombre());
			
			if(rol.getId() != 0) {
				ps.setInt(2, rol.getId());
			}
			
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
	
	/*public boolean addRol(Rol rol) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO roles (nombre) VALUES (?)";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setString(1, rol.getNombre());
			
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
	}*/
	
	//public boolean deleteRol(Rol rol)
	public boolean deleteRol(Rol rol) {
		Connection con = AccesoBD.getConnection();
		PreparedStatement ps = null;
		
		String sql = "DELETE FROM roles WHERE id = ?";
		
		try {
			ps = con.prepareStatement(sql);
			
			ps.setInt(1, rol.getId());
			
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
