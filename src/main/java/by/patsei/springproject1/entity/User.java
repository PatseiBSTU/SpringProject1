package by.patsei.springproject1.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user", schema = "public")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "joined")
	private LocalDate joined;
	
	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private UserCredentials userCredentials;
	
	@OneToOne(mappedBy = "user", cascade = {CascadeType.ALL}, orphanRemoval = true)
	private UserInfo userInfo;
	
	@ManyToMany
	@JoinTable(
			name = "user_follower",
			joinColumns = @JoinColumn(name = "follower_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> followers;
	
	@ManyToMany
	@JoinTable(
			name = "user_follower",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "follower_id")
	)
	private List<User> followed;
	
	public User() {
	}
	
	public User(LocalDate joined) {
		this.joined = joined;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getJoined() {
		return joined;
	}
	
	public void setJoined(LocalDate joined) {
		this.joined = joined;
	}
	
	public UserCredentials getUserCredentials() {
		return userCredentials;
	}
	
	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public List<User> getFollowers() {
		return followers;
	}
	
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	
	public List<User> getFollowed() {
		return followed;
	}
	
	public void setFollowed(List<User> followed) {
		this.followed = followed;
	}
	
}
