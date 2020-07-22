package com.locus.rbac.service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locus.rbac.constant.Constants;
import com.locus.rbac.entity.User;
import com.locus.rbac.enums.Role;
import com.locus.rbac.exception.DataNotFoundException;
import com.locus.rbac.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;

	@Autowired
	public UserService(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> fetchDbUsers() {
		return userRepository.getUsers();
	}

	public User authenticateUser(final String userName, final String password) {
		List<User> users = userRepository.getUsers();
		return users.stream().filter(user -> isCredentialValid(userName, password, user)).findFirst()
				.orElseThrow(() -> new DataNotFoundException(Constants.INVALID_LOGIN));
	}

	public User addUserRole(final String userName, final Role newRole) {
		User existingUser = fetchUserByUserName(userName);
		Set<Role> userRoles = existingUser.getRoles();
		if (Objects.isNull(userRoles)) {
			Set<Role> roles = new HashSet<>();
			roles.add(newRole);
			existingUser.setRoles(roles);
		} else if (userRoles.contains(newRole)) {
			return existingUser;
		} else {
			userRoles.add(newRole);
			existingUser.setRoles(userRoles);
		}
		return existingUser;
	}

	public User removeUserRole(final String userName, final Role role) {
		User existingUser = fetchUserByUserName(userName);
		Set<Role> userRoles = existingUser.getRoles();
		if (!Objects.isNull(userRoles) && userRoles.contains(role)) {
			userRoles.remove(role);
			existingUser.setRoles(userRoles);
		} else {
			throw new DataNotFoundException(Constants.USER_NOT_FOUND);
		}
		return existingUser;
	}

	private User fetchUserByUserName(final String userName) {
		List<User> users = userRepository.getUsers();
		return users.stream().filter(user -> isUserNameValid(userName, user)).findFirst()
				.orElseThrow(() -> new DataNotFoundException(Constants.USER_NOT_FOUND));
	}

	private boolean isCredentialValid(final String userName, final String password, final User user) {
		return StringUtils.equals(user.getUserName(), userName) && StringUtils.equals(user.getPassword(), password);
	}

	private boolean isUserNameValid(final String userName, final User user) {
		return StringUtils.equals(user.getUserName(), userName);
	}
}
