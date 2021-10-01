package by.patsei.springproject1.dto;

import javax.validation.constraints.NotBlank;

public class AuthUserDto {
	
	@NotBlank
	public String login;
	
	@NotBlank
	public String password;
	
	public AuthUserDto() {
	}
	
	public AuthUserDto(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
}
