package com.centrosanluis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.centrosanluis.model.Rol;

public class RolDAO {

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

	public boolean addRol(Rol rol) {
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
	}

}
