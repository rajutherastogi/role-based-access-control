package com.locus.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.locus.rbac.enums.ActionType;
import com.locus.rbac.enums.Resource;
import com.locus.rbac.enums.Role;
import com.locus.rbac.service.RbacManager;

@SpringBootApplication
public class RoleBasedAccessControlApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RoleBasedAccessControlApplication.class, args);
	}

	@Autowired
	private RbacManager rbacManager;

	@Override
	public void run(String... args) throws Exception {
		// Sample test data.
		String userName = "user3", password = "user3";
		Resource resource = Resource.R2;
		Role newRole = Role.MANAGER, currRole = Role.MANAGER;
		ActionType actionType = ActionType.READ;

		// Show users stored in DB.
		rbacManager.showDbUsers();

		// Show resource access based on role stored in DB.
		rbacManager.showDbRoleResourceAccessMap();

		// Check if an user with username and password can access a resource for given
		// actionType.
		rbacManager.checkUserResourceAccess(userName, password, resource, actionType);

		// Add a role to an user with given userName.
		rbacManager.addUserRole(userName, newRole);

		// Remove a role of an user with given userName.
		rbacManager.removeUserRole(userName, currRole);
	}
}
