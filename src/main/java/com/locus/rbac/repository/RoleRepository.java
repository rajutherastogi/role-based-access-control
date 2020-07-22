package com.locus.rbac.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.locus.rbac.enums.ActionType;
import com.locus.rbac.enums.Resource;
import com.locus.rbac.enums.Role;

@Component
public class RoleRepository {
	private Map<Role, Map<ActionType, Set<Resource>>> roleMap;

	@PostConstruct
	public void init() {
		roleMap = new HashMap<>();
		updateRoleMap(roleMap, Role.MANAGER,
				new Resource[] { Resource.R1, Resource.R2, Resource.R3, Resource.R4, Resource.R5, Resource.R6,
						Resource.R7 },
				new Resource[] { Resource.R1, Resource.R2, Resource.R3, Resource.R4, Resource.R5, Resource.R6,
						Resource.R7 },
				new Resource[] { Resource.R1, Resource.R2, Resource.R3, Resource.R4, Resource.R5, Resource.R6,
						Resource.R7 });

		updateRoleMap(roleMap, Role.ASSISTANT, new Resource[] { Resource.R1, Resource.R2, Resource.R3 },
				new Resource[] { Resource.R1 }, new Resource[] { Resource.R1 });

		updateRoleMap(roleMap, Role.GUEST, new Resource[] { Resource.R1 }, new Resource[] {}, new Resource[] {});
	}

	public Map<Role, Map<ActionType, Set<Resource>>> getRoleMap() {
		return roleMap;
	}

	private void updateRoleMap(final Map<Role, Map<ActionType, Set<Resource>>> roleMap, final Role role,
			final Resource[] readResources, final Resource[] writeResources, final Resource[] deleteResources) {
		Map<ActionType, Set<Resource>> actionResourceMap = new HashMap<>();
		actionResourceMap.put(ActionType.READ, new HashSet<>(Arrays.asList((readResources))));
		actionResourceMap.put(ActionType.WRITE, new HashSet<>(Arrays.asList((writeResources))));
		actionResourceMap.put(ActionType.DELETE, new HashSet<>(Arrays.asList((deleteResources))));

		roleMap.put(role, actionResourceMap);
	}
}
