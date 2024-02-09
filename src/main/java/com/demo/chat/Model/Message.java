package com.demo.chat.Model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="message")
public class Message {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
@Column
private String message;
@Column
private LocalDateTime timeMessage;
@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="user_id")
private User user;

@ManyToOne(fetch=FetchType.LAZY)
@JoinColumn(name="topic_id")
private TopicMessage topicMessage;

public Message() {
	
}

public Message(long id, String message, LocalDateTime timeMessage, User user, TopicMessage topicMessage) {
	super();
	this.id = id;
	this.message = message;
	this.timeMessage = timeMessage;
	this.user = user;
	this.topicMessage = topicMessage;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public LocalDateTime getTimeMessage() {
	return timeMessage;
}

public void setTimeMessage(LocalDateTime timeMessage) {
	this.timeMessage = timeMessage;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public TopicMessage getTopicMessage() {
	return topicMessage;
}

public void setTopicMessage(TopicMessage topicMessage) {
	this.topicMessage = topicMessage;
}


}
