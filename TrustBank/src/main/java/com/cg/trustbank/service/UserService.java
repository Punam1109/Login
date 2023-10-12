package com.cg.trustbank.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.trustbank.dao.RoleDao;
import com.cg.trustbank.dao.UserDao;
import com.cg.trustbank.entity.Role;
import com.cg.trustbank.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(User user) {
		Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
	}
	
	//Enter Two Role And Two user into the table
	
	public void initRoleAnsUser() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		adminRole.setRoleDescription("Admin role");
		roleDao.save(adminRole);
		
		Role userRole = new Role();
		userRole.setRoleName("User");
		userRole.setRoleDescription("Default role for newly created record");
		roleDao.save(userRole);
		
		User adminUser = new User();
		adminUser.setUserFirstName("admin");
		adminUser.setUserLastName("admin");
		adminUser.setUserName("admin");
		adminUser.setUserPassword(getEncodedPassword("admin@pass"));
		Set<Role> adminRoles = new HashSet<>();
		adminRoles.add(adminRole);
		adminUser.setRoles(adminRoles);
		userDao.save(adminUser);
		
//		User user = new User();
//		user.setUserFirstName("jyoti");
//		user.setUserLastName("prajapati");
//		user.setUserName("jyoti123");
//		user.setUserPassword(getEncodedPassword("jyoti@pass"));
//		Set<Role> userRoles = new HashSet<>();
//		userRoles.add(userRole);
//		user.setRole(userRoles);
//		userDao.save(user);
	}
	
	public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

	
