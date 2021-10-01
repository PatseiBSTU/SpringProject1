package by.patsei.springproject1.repository;

import by.patsei.springproject1.entity.Project;
import by.patsei.springproject1.entity.Role;
import by.patsei.springproject1.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	
	Optional<Role> findByUserAndProject(User user, Project project);
	
}
