package by.patsei.springproject1.service;


import by.patsei.springproject1.dto.NewActivityDto;
import by.patsei.springproject1.entity.Activity;
import by.patsei.springproject1.entity.Project;
import by.patsei.springproject1.entity.Task;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.entity.enums.UserRole;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.repository.ActivityRepository;
import by.patsei.springproject1.repository.TaskRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityService {
	
	private final ActivityRepository activityRepository;
	private final TaskRepository taskRepository;
	private final RoleService roleService;
	private TaskService taskService;
	private final UserService userService;
	private final ProjectService projectService;
	
	@Autowired
	public ActivityService(ActivityRepository activityRepository, RoleService roleService, UserService userService, TaskRepository taskRepository, ProjectService projectService) {
		this.activityRepository = activityRepository;
		this.roleService = roleService;
		this.userService = userService;
		this.taskRepository = taskRepository;
		this.projectService = projectService;
	}
	
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	public Activity get(User user, Long activityId) throws NoSuchEntityException, AuthorizationException {
		Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NoSuchEntityException("no such activity"));
		
		roleService.authorize(user, activity.getTask().getProject(), UserRole.VIEWER);
		
		return activity;
	}
	
	public Activity create(User user, NewActivityDto newActivityDto) throws NoSuchEntityException, AuthorizationException {
		if (newActivityDto.status == null && newActivityDto.elapsed == null && newActivityDto.assigneeLogin == null)
			throw new InvalidParameterException("empty activities are not allowed");
		
		Task task = taskService.get(user, newActivityDto.taskId);
		
		roleService.authorize(user, task.getProject(), UserRole.COLLABORATOR);
		
		Activity activity = new Activity(
				task,
				user,
				!Strings.isBlank(newActivityDto.assigneeLogin)
						? userService.getUser(user, newActivityDto.assigneeLogin)
						: null,
				newActivityDto.status,
				newActivityDto.elapsed,
				LocalDateTime.now(),
				newActivityDto.description
		);
		
		task.getActivities().add(activity);
		
		taskRepository.save(task);
		
		return activity;
	}
	
	public List<Activity> allByTask(User user, Long taskId, Pageable pageable) throws NoSuchEntityException, AuthorizationException {
		Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchEntityException("no such task"));
		
		roleService.authorize(user, task.getProject(), UserRole.VIEWER);
		
		return activityRepository.findAllByTask(task, pageable);
	}
	
	public List<Activity> allByProject(User user, Long projectId, Pageable pageable) throws AuthorizationException, NoSuchEntityException {
		Project project = projectService.get(user, projectId);
		
		return activityRepository.findAllByProject(project, pageable);
	}
	
	public void delete(User user, Long activityId) throws NoSuchEntityException, AuthorizationException {
		Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new NoSuchEntityException("no such activity"));
		
		if (!(roleService.hasPermission(user, activity.getTask().getProject(), UserRole.COLLABORATOR) ||
				(activity.getTask().getCreator().getId().equals(user.getId()))))
			throw new AuthorizationException("no permission");
		
		activityRepository.delete(activity);
	}
	
	public List<Activity> allByUser(User user, Pageable pageable) {
//		TODO: followers visible activities
		return activityRepository.findAllByUser(user, pageable);
	}
	
}
