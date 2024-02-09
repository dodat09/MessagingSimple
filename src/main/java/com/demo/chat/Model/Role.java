package com.demo.chat.Model;

import javax.persistence.CascadeType;
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
@Table(name="role")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String rolename;
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User roleUser;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="topic_id")
	private TopicMessage roleTopic;
	
	public Role() {
		
	}

	public Role(int id, String rolename, User roleUser, TopicMessage roleTopic) {
		super();
		this.id = id;
		this.rolename = rolename;
		this.roleUser = roleUser;
		this.roleTopic = roleTopic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public User getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(User roleUser) {
		this.roleUser = roleUser;
	}

	public TopicMessage getRoleTopic() {
		return roleTopic;
	}

	public void setRoleTopic(TopicMessage roleTopic) {
		this.roleTopic = roleTopic;
	}

	
}
