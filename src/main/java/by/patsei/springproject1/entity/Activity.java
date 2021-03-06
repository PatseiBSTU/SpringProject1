package by.patsei.springproject1.entity;



import by.patsei.springproject1.entity.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

//TODO: priority
@Entity
@Table(name = "activity")
public class Activity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "creator_id")
	private User creator;
	
	@ManyToOne
	@JoinColumn(name = "assignee_id")
	private User assignee;
	
	@Enumerated
	@Column(name = "status")
	private TaskStatus status;
	
	@Column(name = "elapsed")
	private Double elapsed;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp;
	
	@Column(name = "description")
	private String description;
	
	public Activity() {
	}
	
	public Activity(Task task, User creator, User assignee, TaskStatus status, Double elapsed, LocalDateTime timestamp, String description) {
		this.task = task;
		this.creator = creator;
		this.assignee = assignee;
		this.status = status;
		this.elapsed = elapsed;
		this.timestamp = timestamp;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Task getTask() {
		return task;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public User getCreator() {
		return creator;
	}
	
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	public User getAssignee() {
		return assignee;
	}
	
	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
	
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public Double getElapsed() {
		return elapsed;
	}
	
	public void setElapsed(Double elapsed) {
		this.elapsed = elapsed;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
