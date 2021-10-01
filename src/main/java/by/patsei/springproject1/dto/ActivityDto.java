package by.patsei.springproject1.dto;

import by.patsei.springproject1.entity.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ActivityDto {
	
	public Long id;
	public TaskDto task;
	public UserDto creator;
	public UserDto assignee;
	public TaskStatus status;
	public String description;
	public Double elapsed;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public LocalDateTime timestamp;
	
	public ActivityDto() {
	}
	
	public TaskDto getTask() {
		return task;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTask(TaskDto task) {
		this.task = task;
	}
	
}
