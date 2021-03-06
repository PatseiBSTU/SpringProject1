package by.patsei.springproject1.entity;

import javax.persistence.*;

@Entity
@Table(name = "task_info")
public class TaskInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "task_id")
	private Task task;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	public TaskInfo() {
	}
	
	public TaskInfo(Task task, String name, String description) {
		this.task = task;
		this.name = name;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
}
