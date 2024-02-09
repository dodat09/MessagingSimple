package com.demo.chat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.chat.Service.TopicService;

@RestController
@RequestMapping(name="/user")
public class TopicController {
   @Autowired
   private TopicService topicService;
   
   @PostMapping("/createTopic")
   public String create(@RequestParam("nameTopic") String nameTopic) {
	   topicService.createTopic(nameTopic);
	   return "Thành Công";
   }
}
