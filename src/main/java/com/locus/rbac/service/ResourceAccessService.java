package com.locus.rbac.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locus.rbac.enums.ActionType;
import com.locus.rbac.enums.Resource;
import com.locus.rbac.enums.Role;
import com.locus.rbac.repository.RoleRepository;

@Service
public class ResourceAccessService {
	private final RoleRepository roleRepository;

	@Autowired
	public ResourceAccessService(final RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Map<Role, Map<ActionType, Set<Resource>>> fetchRoleMap() {
		return roleRepository.getRoleMap();
	}

	public boolean isAccessible(final Set<Role> roles, final Resource resource, final ActionType actionType) {
		Map<ActionType, Set<Resource>> actionResourcesMap = fetchAccessInformation(roles);
		Set<Resource> resources = actionResourcesMap.get(actionType);
		return Objects.isNull(resources) ? false : resources.contains(resource);
	}

	private Map<ActionType, Set<Resource>> fetchAccessInformation(final Set<Role> roles) {
		Map<Role, Map<ActionType, Set<Resource>>> roleMap = roleRepository.getRoleMap();
		Map<ActionType, Set<Resource>> resourceMap = new HashMap<>();
		roles.stream().forEach(role -> {
			Map<ActionType, Set<Resource>> currResourceMap = roleMap.get(role);
			currResourceMap.forEach((actionType, resources) -> {
				if (resourceMap.containsKey(actionType)) {
					resourceMap.get(actionType).addAll(resources);
				} else {
					resourceMap.put(actionType, resources);
				}
			});
		});
		return resourceMap;
	}
}
