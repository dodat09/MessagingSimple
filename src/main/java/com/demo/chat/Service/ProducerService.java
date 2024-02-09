package com.demo.chat.Service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.chat.Model.Message;
import com.demo.chat.Model.TopicMessage;
import com.demo.chat.Model.User;
import com.demo.chat.Model.dto.MessageDto;
import com.demo.chat.Repository.MessageRepo;
import com.demo.chat.Repository.TopicRepo;
import com.demo.chat.Repository.UserRepo;

@Service
public class ProducerService {
     private final static Logger logger = LoggerFactory.getLogger(ProducerService.class);
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MessageRepo messageRepo;
	@Autowired
	private TopicRepo topicRepo;
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	public String sendMessage(MessageDto messageDto,String topic) {
		TopicMessage topicMessage = topicRepo.findByName(topic);
		User user = userRepo.findUserById(messageDto.getUser_id());
	    
		if(user==null) {
			return "Thong tin user khong ton tai";
		}else {
		String message = user.getUsername()+":"+messageDto.getMessage();
		Message me = new Message();
		me.setTimeMessage(LocalDateTime.now());
		me.setMessage(message);
		me.setTopicMessage(topicMessage);
		me.setUser(user);
		kafkaTemplate.send(topic,message);
		messageRepo.save(me);
		return "Send message Success";
		}	
	}
}
