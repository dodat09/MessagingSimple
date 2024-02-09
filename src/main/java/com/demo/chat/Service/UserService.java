package com.demo.chat.Service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.chat.Model.TopicMessage;
import com.demo.chat.Model.User;
import com.demo.chat.Repository.TopicRepo;
import com.demo.chat.Repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TopicRepo topicRepo;
	
	public void createUser(User user) {
		user.setCreateTime(LocalDateTime.now());
		userRepo.save(user);
	}
	public void addUserInTopic(String userName,String topic) {
		User user = userRepo.findByUsername(userName);
		if(user==null) {
			System.out.println("Thong tin user ko ton tai "); 
		}
		TopicMessage topicMessage = topicRepo.findByName(topic);
		topicMessage.getUser().add(user);
		topicRepo.save(topicMessage);
		System.out.println("Join Success");
		}
}
