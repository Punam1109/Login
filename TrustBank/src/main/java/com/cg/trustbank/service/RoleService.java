package com.cg.trustbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.trustbank.dao.RoleDao;
import com.cg.trustbank.entity.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	
	public Role createNewRole(Role role) {
		return roleDao.save(role);
	}

}
