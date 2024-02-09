package com.demo.chat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.chat.Model.User;
import com.demo.chat.Service.TopicService;
import com.demo.chat.Service.UserService;

@RestController
@RequestMapping(name="/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private TopicService topicService;
	
//	@PostMapping("/sighup")
//	public String createUser(@RequestBody User user) {
//		userService.createUser(user);
//		return "khoi tao user thanh cong";
//	}
	@PostMapping("/joinTopic/{username}/{topic}")
	public String joinTopic(@PathVariable("username")String username,@PathVariable("topic") String topic) {
		
		userService.addUserInTopic(username, topic);
		return "Them user vao topic "+topic+" thanh cong";
	}
}
