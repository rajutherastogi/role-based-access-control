package com.locus.rbac.repository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.locus.rbac.entity.User;
import com.locus.rbac.enums.Role;

@Component
public class UserRepository {
	private List<User> users;

	@PostConstruct
	public void init() {
		this.users = Arrays.asList(
				new User[] { new User("user1", "user1", new HashSet(Arrays.asList(new Role[] { Role.MANAGER }))),
						new User("user2", "user2", new HashSet(Arrays.asList(new Role[] { Role.ASSISTANT }))),
						new User("user3", "user3", new HashSet(Arrays.asList(new Role[] { Role.GUEST }))) });
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
