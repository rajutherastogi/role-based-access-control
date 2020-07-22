package com.locus.rbac.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.locus.rbac.entity.User;
import com.locus.rbac.enums.ActionType;
import com.locus.rbac.enums.Resource;
import com.locus.rbac.enums.Role;
import com.locus.rbac.exception.DataNotFoundException;

@Service
public class RbacManager {

	private final ResourceAccessService resourceAccessService;
	private final UserService userService;

	public RbacManager(final ResourceAccessService resourceAccessService, final UserService userService) {
		this.resourceAccessService = resourceAccessService;
		this.userService = userService;
	}

	public void showDbRoleResourceAccessMap() {
		System.out.println(String.format("RoleMap found: %s", resourceAccessService.fetchRoleMap()));
	}

	public void checkUserResourceAccess(final String userName, final String password, final Resource resource,
			final ActionType actionType) {
		try {
			User user = userService.authenticateUser(userName, password);
			if (Objects.isNull(user.getRoles()) && user.getRoles().isEmpty()) {
				System.out.println(String.format("%s can't access the resource: %s", userName, resource));
			}
			if (resourceAccessService.isAccessible(user.getRoles(), resource, actionType)) {
				System.out.println(String.format("%s can access the resource: %s", userName, resource));
			} else {
				System.out.println(String.format("%s can't access the resource: %s", userName, resource));
			}
		} catch (DataNotFoundException dataNotFoundException) {
			System.out.println(String.format("404 Not Found: %s", dataNotFoundException.getMessage()));
		} catch (Exception exception) {
			System.out.println(String.format("500 Some Internal Error Occured: %s", exception));
		}
	}

	public void showDbUsers() {
		System.out.println(String.format("Users found: %s", userService.fetchDbUsers()));
	}

	public void addUserRole(final String userName, final Role newRole) {
		try {
			User user = userService.addUserRole(userName, newRole);
			System.out.println(String.format("User after adding new role: %s, user: %s", newRole, user.toString()));
		} catch (DataNotFoundException dataNotFoundException) {
			System.out.println(String.format("404 Not Found: %s", dataNotFoundException.getMessage()));
		} catch (Exception exception) {
			System.out.println(String.format("500 Some Internal Error Occured: %s", exception));
		}
	}

	public void removeUserRole(final String userName, final Role role) {
		try {
			User user = userService.removeUserRole(userName, role);
			System.out.println(String.format("User after removing current role: %s, user: %s", role, user.toString()));
		} catch (DataNotFoundException dataNotFoundException) {
			System.out.println(String.format("404 Not Found: %s", dataNotFoundException.getMessage()));
		} catch (Exception exception) {
			System.out.println(String.format("500 Some Internal Error Occured: %s", exception));
		}
	}
}
