package com.centrosanluis.service;

import java.util.List;

import com.centrosanluis.dao.RolDAO;
import com.centrosanluis.model.Rol;

public class RolService {
	
	private RolDAO rolDAO;
	
	public RolService() {
		rolDAO = new RolDAO();
	}

	public List<Rol> getRoles() {
		return rolDAO.getRoles();
	}

	public boolean addRol(Rol rol) {
		return rolDAO.createOrUpdateRol(rol);
	}
	
	public boolean updateRol(Rol rol) {
		if(rol.getId()!=0){
			return rolDAO.createOrUpdateRol(rol);
		}else {
			return false;
		}
	}

}
