package by.patsei.springproject1.dto;

import by.patsei.springproject1.entity.enums.TaskStatus;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class NewActivityDto {
	
	@NotNull
	public Long taskId;
	
	public TaskStatus status;
	public String description;
	
	@Min(value = 0, message = "elapsed time cannot be negative")
	public Double elapsed;
	
	public String assigneeLogin;
	
	public NewActivityDto() {
	}
	
	public NewActivityDto(@NotNull Long taskId, TaskStatus status, String description, @Min(value = 0, message = "elapsed time cannot be negative") Double elapsed, String assigneeLogin) {
		this.taskId = taskId;
		this.status = status;
		this.description = description;
		this.elapsed = elapsed;
		this.assigneeLogin = assigneeLogin;
	}
	
}
