package com.demo.chat.Model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY)
private int id;
@Column
private String username;
@Column
private String password;

@Column
private LocalDateTime createTime;
@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
private List<Message> listMessage;
@ManyToMany(mappedBy="user",fetch=FetchType.LAZY)
private Set<TopicMessage> listTopic = new HashSet<>();
@OneToMany(mappedBy="roleUser")
private Set<Role> userRole = new HashSet<>();

public User() {

}
public User(int id, String username, String password, LocalDateTime createTime, List<Message> listMessage,
		Set<TopicMessage> listTopic, Set<Role> userRole) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.createTime = createTime;
	this.listMessage = listMessage;
	this.listTopic = listTopic;
	this.userRole = userRole;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public LocalDateTime getCreateTime() {
	return createTime;
}
public void setCreateTime(LocalDateTime createTime) {
	this.createTime = createTime;
}
public List<Message> getListMessage() {
	return listMessage;
}
public void setListMessage(List<Message> listMessage) {
	this.listMessage = listMessage;
}
public Set<TopicMessage> getListTopic() {
	return listTopic;
}
public void setListTopic(Set<TopicMessage> listTopic) {
	this.listTopic = listTopic;
}
public Set<Role> getUserRole() {
	return userRole;
}
public void setUserRole(Set<Role> userRole) {
	this.userRole = userRole;
}

}
