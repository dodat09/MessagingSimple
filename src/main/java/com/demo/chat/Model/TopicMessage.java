package com.demo.chat.Model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="topic")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicMessage {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id ;
@Column
private String nameTopic;
@ManyToMany(cascade=CascadeType.ALL)
@JoinTable(name="Topic_User",
            joinColumns = @JoinColumn(name="topic_id"),
            inverseJoinColumns=@JoinColumn(name="user_id"))
private Set<User> user = new HashSet();
@OneToMany(mappedBy="topicMessage",cascade=CascadeType.ALL)
private List<Message> listMessageTopic;

@OneToMany(mappedBy="roleTopic",cascade=CascadeType.ALL)
private Set<Role> roleTopic = new HashSet<>(); 
public TopicMessage() {
	
}
public TopicMessage(int id, String nameTopic, Set<User> user, List<Message> listMessageTopic, Set<Role> roleTopic) {
	super();
	this.id = id;
	this.nameTopic = nameTopic;
	this.user = user;
	this.listMessageTopic = listMessageTopic;
	this.roleTopic = roleTopic;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNameTopic() {
	return nameTopic;
}
public void setNameTopic(String nameTopic) {
	this.nameTopic = nameTopic;
}
public Set<User> getUser() {
	return user;
}
public void setUser(Set<User> user) {
	this.user = user;
}
public List<Message> getListMessageTopic() {
	return listMessageTopic;
}
public void setListMessageTopic(List<Message> listMessageTopic) {
	this.listMessageTopic = listMessageTopic;
}
public Set<Role> getRoleTopic() {
	return roleTopic;
}
public void setRoleTopic(Set<Role> roleTopic) {
	this.roleTopic = roleTopic;
}

}
