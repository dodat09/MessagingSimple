package com.demo.chat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.chat.Model.TopicMessage;
import com.demo.chat.Repository.TopicRepo;
import com.demo.chat.Repository.UserRepo;

@Service
public class TopicService {

	@Autowired
	private TopicRepo topicRepo;
	@Autowired
	private UserRepo userRepo;
	
	public void createTopic(String topicName) {
		TopicMessage topic = new TopicMessage();
		topic.setNameTopic(topicName);
		topicRepo.save(topic);
	}
}
