package com.demo.chat.Model.dto;

public class MessageDto {

  private int user_id;
  private String message;
public MessageDto() {
	
}
public MessageDto(int user_id, String message) {
	super();
	this.user_id = user_id;
	this.message = message;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
} 
}
