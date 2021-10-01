package by.patsei.springproject1.controller;

import by.patsei.springproject1.dto.AuthUserDto;
import by.patsei.springproject1.dto.UserDto;
import by.patsei.springproject1.entity.User;
import by.patsei.springproject1.exceptions.AuthenticationException;
import by.patsei.springproject1.exceptions.NoSuchEntityException;
import by.patsei.springproject1.service.UserService;
import by.patsei.springproject1.util.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	private final UserService userService;
	
	@Autowired
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public String authenticate(@Valid @RequestBody AuthUserDto authUserDto) throws AuthenticationException, NoSuchEntityException {
		return userService.authenticate(authUserDto);
	}
	
	@GetMapping("validate")
	public UserDto validate(@RequestHeader("token") String token) throws NoSuchEntityException {
		User user = userService.validate(token);
		return Mapper.map(user, UserDto.class);
	}
	
}
