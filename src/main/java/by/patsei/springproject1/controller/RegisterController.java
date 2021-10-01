package by.patsei.springproject1.controller;

import by.patsei.springproject1.dto.RegisterUserDto;
import by.patsei.springproject1.exceptions.RegistrationException;
import by.patsei.springproject1.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("register")
public class RegisterController {
	
	private final UserService userService;
	
	@Autowired
	public RegisterController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public void register(@Valid @RequestBody RegisterUserDto registerUserDto) throws RegistrationException {
		userService.register(registerUserDto);
	}
	
}
