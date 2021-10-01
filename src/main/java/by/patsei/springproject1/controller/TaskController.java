package by.patsei.springproject1.controller;

import by.patsei.springproject1.dto.EditTaskDto;
import by.patsei.springproject1.dto.NewTaskDto;
import by.patsei.springproject1.dto.TaskDto;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.service.TaskService;

import by.patsei.springproject1.service.UserService;
import by.patsei.springproject1.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
public class TaskController {
	
	private final TaskService taskService;
	private final UserService userService;
	
	@Autowired
	public TaskController(TaskService taskService, UserService userService) {
		this.taskService = taskService;
		this.userService = userService;
	}
	
	@GetMapping("all")
	public List<TaskDto> all(@RequestHeader("token") String token,
							 @RequestParam("projectId") Long projectId,
							 @PageableDefault(direction = Sort.Direction.DESC, sort = {"opened"}) Pageable pageable) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(taskService.all(user, projectId, pageable), TaskDto.class)
				.stream()
				.map(TaskDto::compute)
				.collect(Collectors.toList());
	}
	
	@GetMapping("owned")
	public List<TaskDto> owned(@RequestHeader("token") String token,
	                           @PageableDefault(direction = Sort.Direction.DESC, sort = {"opened"}) Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(taskService.owned(user, pageable), TaskDto.class).stream().map(TaskDto::compute).collect(Collectors.toList());
	}
	
	@GetMapping("assignee")
	public List<TaskDto> assignee(@RequestHeader("token") String token,
	                              @PageableDefault(direction = Sort.Direction.DESC, sort = {"opened"}) Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(taskService.assignee(user, pageable), TaskDto.class).stream().map(TaskDto::compute).collect(Collectors.toList());
	}
	
	@GetMapping("get")
	public TaskDto get(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(taskService.get(user, taskId), TaskDto.class).compute();
	}
	
	@PostMapping("create")
	public TaskDto create(@RequestHeader("token") String token, @Valid @RequestBody NewTaskDto newTaskDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(taskService.create(user, newTaskDto), TaskDto.class).compute();
	}
	
	@PostMapping("edit")
	public TaskDto edit(@RequestHeader("token") String token, @Valid @RequestBody EditTaskDto editTaskDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(taskService.edit(user, editTaskDto), TaskDto.class).compute();
	}
	
	@GetMapping("delete")
	public void delete(@RequestHeader("token") String token, @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		taskService.delete(user, taskId);
	}
	
}
