package com.locus.rbac.entity;

import java.util.Set;

import com.locus.rbac.enums.Role;

public class User {
	private String userName;
	private String password;
	private Set<Role> roles;

	public User(String userName, String password, Set<Role> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.roles = roles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", roles=" + roles + "]";
	}

}
