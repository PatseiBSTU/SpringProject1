package by.patsei.springproject1.service;


import by.patsei.springproject1.entity.Project;
import by.patsei.springproject1.entity.Role;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.entity.enums.UserRole;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public UserRole getRole(User user, Project project) {
		Optional<Role> roleOptional = roleRepository.findByUserAndProject(user, project);
		
		if (!roleOptional.isPresent()) return UserRole.UNAUTHORIZED;
		
		return roleOptional.get().getRole();
	}

	public boolean hasPermission(User user, Project project, UserRole role) {
		if (!project.getPublic() && role == UserRole.UNAUTHORIZED) return false;
		return getRole(user, project).level >= role.level;
	}
	
	public void authorize(User user, Project project, UserRole role) throws AuthorizationException {
		if (!hasPermission(user, project, role)) throw new AuthorizationException("no permission");
	}
	
}
