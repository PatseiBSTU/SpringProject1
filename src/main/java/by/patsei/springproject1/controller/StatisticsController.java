package by.patsei.springproject1.controller;

import by.patsei.springproject1.dto.ProjectActivityDto;
import by.patsei.springproject1.dto.ProjectTaskTypeDto;
import by.patsei.springproject1.entity.Project;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.service.ProjectService;
import by.patsei.springproject1.service.StatisticsService;
import by.patsei.springproject1.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("stats")
public class StatisticsController {
	
	private final UserService userService;
	private final ProjectService projectService;
	private final StatisticsService statisticsService;
	
	@Autowired
	public StatisticsController(UserService userService, ProjectService projectService, StatisticsService statisticsService) {
		this.userService = userService;
		this.projectService = projectService;
		this.statisticsService = statisticsService;
	}
	
	@GetMapping("project-activity")
	public List<ProjectActivityDto> getProjectActivities(@RequestHeader("token") String token,
														 @RequestParam("projectId") Long projectId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);
		
		return statisticsService.getProjectActivities(user, project);
	}
	
	@GetMapping("project-task-type")
	public List<ProjectTaskTypeDto> getProjectTaskTypes(@RequestHeader("token") String token,
														@RequestParam("projectId") Long projectId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		Project project = projectService.get(user, projectId);
		
		return statisticsService.getProjectTaskTypes(user, project);
	}
	
}
