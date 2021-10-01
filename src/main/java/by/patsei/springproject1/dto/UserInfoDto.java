package by.patsei.springproject1.dto;

public class UserInfoDto {
	
	public Integer id;
	public String name;
	public String bio;
	public String url;
	public String company;
	public String location;
	
	public UserInfoDto() {
	}
	
	public UserInfoDto(Integer id, String name, String bio, String url, String company, String location) {
		this.id = id;
		this.name = name;
		this.bio = bio;
		this.url = url;
		this.company = company;
		this.location = location;
	}
	
}
