package by.patsei.springproject1.controller;


import by.patsei.springproject1.dto.EditProjectDto;
import by.patsei.springproject1.dto.NewProjectDto;
import by.patsei.springproject1.dto.ProjectDto;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.DuplicationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.service.ProjectService;
import by.patsei.springproject1.service.UserService;

import by.patsei.springproject1.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
	
	private final UserService userService;
	private final ProjectService projectService;
	
	@Autowired
	public ProjectController(UserService userService, ProjectService projectService) {
		this.userService = userService;
		this.projectService = projectService;
	}
	
	@PostMapping("create")
	public ProjectDto create(@RequestHeader("token") String token,
							 @Valid @RequestBody NewProjectDto newProjectDto) throws NoSuchEntityException, DuplicationException {
		User user = userService.validate(token);
		
		return projectService.create(user, newProjectDto);
	}
	
	@PostMapping("edit")
	public ProjectDto edit(@RequestHeader("token") String token,
	                       @Valid @RequestBody EditProjectDto editProjectDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(projectService.edit(user, editProjectDto), ProjectDto.class);
	}
	
	@GetMapping("all")
	public List<ProjectDto> all(@RequestHeader("token") String token,
	                            @PageableDefault(direction = Sort.Direction.DESC, sort = "created") Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(projectService.all(user, pageable), ProjectDto.class);
	}
	
	@GetMapping("/{login}/{name}/get")
	public ProjectDto get(@RequestHeader("token") String token,
	                      @PathVariable String login,
	                      @PathVariable String name) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(projectService.get(user, login, name), ProjectDto.class);
	}
	
	@GetMapping("delete")
	public void delete(@RequestHeader("token") String token,
	                   @RequestParam("projectId") Long projectId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		projectService.delete(user, projectId);
	}
	
	@GetMapping("find")
	public List<ProjectDto> find(@RequestHeader("token") String token,
	                             @RequestParam("search") String search,
	                             @PageableDefault(direction = Sort.Direction.DESC, sort = "created") Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		if (search.trim().isEmpty()) return all(token, pageable);
		return Mapper.mapAll(projectService.find(user, search, pageable), ProjectDto.class);
	}
	
}
