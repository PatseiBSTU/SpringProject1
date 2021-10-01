package by.patsei.springproject1.controller;


import by.patsei.springproject1.dto.ActivityDto;
import by.patsei.springproject1.dto.NewActivityDto;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.service.ActivityService;
import by.patsei.springproject1.service.UserService;
import by.patsei.springproject1.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {
	
	private final UserService userService;
	private final ActivityService activityService;
	
	@Autowired
	public ActivityController(UserService userService, ActivityService activityService) {
		this.userService = userService;
		this.activityService = activityService;
	}
	
	@GetMapping("allByTask")
	public List<ActivityDto> allByTask(@RequestHeader("token") String token,
									   @RequestParam("taskId") Long taskId,
									   @PageableDefault(direction = Sort.Direction.DESC, sort = "timestamp") Pageable pageable) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(activityService.allByTask(user, taskId, pageable), ActivityDto.class);
	}
	
	@GetMapping("allByProject")
	public List<ActivityDto> allByProject(@RequestHeader("token") String token,
	                                      @RequestParam("projectId") Long projectId,
	                                      @PageableDefault(direction = Sort.Direction.DESC, sort = "timestamp") Pageable pageable) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(activityService.allByProject(user, projectId, pageable), ActivityDto.class);
	}
	
	@GetMapping("allByUser")
	public List<ActivityDto> allByUser(@RequestHeader("token") String token,
	                                   @PageableDefault(direction = Sort.Direction.DESC, sort = "timestamp") Pageable pageable) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.mapAll(activityService.allByUser(user, pageable), ActivityDto.class);
	}
	
	@GetMapping("getLastByTask")
	public ActivityDto getLastByTask(@RequestHeader("token") String token,
	                                 @RequestParam("taskId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		return allByTask(token, taskId, PageRequest.of(0, 1, Sort.Direction.DESC, "timestamp"))
				.stream()
				.findFirst().orElseThrow(() -> new NoSuchEntityException("no such activity"));
	}
	
	@GetMapping("get")
	public ActivityDto get(@RequestHeader("token") String token,
	                       @RequestParam("activityId") Long taskId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(activityService.get(user, taskId), ActivityDto.class);
	}
	
	@PostMapping("create")
	public ActivityDto create(@RequestHeader("token") String token,
	                          @Valid @RequestBody NewActivityDto newTaskDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(activityService.create(user, newTaskDto), ActivityDto.class);
	}
	
	@GetMapping("delete")
	public void delete(@RequestHeader("token") String token, @RequestParam("activityId") Long activityId) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		activityService.delete(user, activityId);
	}
	
}
