package by.patsei.springproject1.service;

import by.patsei.springproject1.dto.EditProjectDto;
import by.patsei.springproject1.dto.NewProjectDto;
import by.patsei.springproject1.dto.ProjectDto;
import by.patsei.springproject1.entity.Project;
import by.patsei.springproject1.entity.ProjectInfo;
import by.patsei.springproject1.entity.Role;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.entity.enums.UserRole;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.DuplicationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.repository.ProjectRepository;
import by.patsei.springproject1.repository.RoleRepository;

import by.patsei.springproject1.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {
	
	private final RoleService roleService;
	private final ProjectRepository projectRepository;
	private final RoleRepository roleRepository;
	
	@Autowired
	public ProjectService(ProjectRepository projectRepository, RoleRepository roleRepository, RoleService roleService) {
		this.projectRepository = projectRepository;
		this.roleRepository = roleRepository;
		this.roleService = roleService;
	}
	
	public ProjectDto create(User user, NewProjectDto newProjectDto) throws DuplicationException {
		if (projectRepository.findByNameAndUser(newProjectDto.name, user.getId()).isPresent())
			throw new DuplicationException("such project already exist");
		
		Project project = projectRepository.save(
				new Project(
						user,
						newProjectDto.isPublic,
						LocalDateTime.now()
				)
		);
		
		ProjectInfo projectInfo = new ProjectInfo(
				project,
				newProjectDto.name,
				newProjectDto.description,
				newProjectDto.about
		);
		
		project.setProjectInfo(projectInfo);
		
		projectRepository.save(project);
		
		roleRepository.save(new Role(project, user, UserRole.OWNER));

//		TODO: feature: progress
		return Mapper.map(project, ProjectDto.class);
	}
	
	public Project edit(User user, EditProjectDto editProjectDto) throws NoSuchEntityException, AuthorizationException {
		Project project = this.projectRepository.findById(editProjectDto.id).orElseThrow(() -> new NoSuchEntityException("no such project to edit"));
		
		roleService.authorize(user, project, UserRole.COLLABORATOR);
		
		project.setPublic(editProjectDto.isPublic);
		project.getProjectInfo().setName(editProjectDto.name);
		project.getProjectInfo().setDescription(editProjectDto.description);
		project.getProjectInfo().setAbout(editProjectDto.about);
		
		return projectRepository.save(project);
	}
	
	public List<Project> all(User user, Pageable pageable) {
		return projectRepository.findAllByUser(user, pageable);
	}
	
	public Project get(User user, String login, String name) throws NoSuchEntityException, AuthorizationException {
		Project project = projectRepository.findByLoginAndName(login, name).orElseThrow(() -> new NoSuchEntityException("no such project"));
		
		roleService.authorize(user, project, UserRole.VIEWER);
		
		return project;
	}
	
	public Project get(User user, Long projectId) throws NoSuchEntityException, AuthorizationException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchEntityException("no such project"));
		
		return get(user, project.getUser().getUserCredentials().getLogin(), project.getProjectInfo().getName());
	}
	
	public void delete(User user, Long projectId) throws NoSuchEntityException, AuthorizationException {
		Project project = projectRepository.findById(projectId).orElseThrow(() -> new NoSuchEntityException("no such project"));
		
		roleService.authorize(user, project, UserRole.OWNER);
		
		projectRepository.delete(project);
	}
	
	public List<Project> find(User user, String search, Pageable pageable) {
		return projectRepository.find(user, search, pageable);
	}
	
}
