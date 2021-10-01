package by.patsei.springproject1.controller;


import by.patsei.springproject1.dto.EditUserDto;
import by.patsei.springproject1.dto.UserDto;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.exceptions.AuthorizationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.service.UserService;
import by.patsei.springproject1.util.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("profile")
public class ProfileController {
	
	private final UserService userService;
	
	public ProfileController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("edit")
	public UserDto edit(@RequestHeader("token") String token,
						@Valid @RequestBody EditUserDto editUserDto) throws NoSuchEntityException, AuthorizationException {
		User user = userService.validate(token);
		
		return Mapper.map(userService.edit(user, editUserDto), UserDto.class);
	}
	
	@GetMapping("/{login}/get")
	public UserDto get(@RequestHeader("token") String token,
	                   @PathVariable String login) throws NoSuchEntityException {
		User user = userService.validate(token);
		
		return Mapper.map(userService.getUser(user, login), UserDto.class);
	}
	
}
