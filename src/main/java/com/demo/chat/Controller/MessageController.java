package com.demo.chat.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.chat.Model.dto.MessageDto;
import com.demo.chat.Service.ConsumerService;
import com.demo.chat.Service.ProducerService;

@RestController
@RequestMapping(value="/", method = { RequestMethod.GET, RequestMethod.POST })
public class MessageController {
    private final static Logger LOGGER = LoggerFactory.getLogger(MessageController.class);
    private final RestTemplate restTemplate;
	@Autowired
	private ConsumerService consumerService;
	@Autowired
	private ProducerService producerService;
	
	public MessageController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	@PostMapping("/send/{topic}")
	public ResponseEntity<String> sendMessageInWeb(@RequestBody MessageDto messageDto,Model model,@PathVariable String topic)  {
		String a = producerService.sendMessage(messageDto,topic);
		LOGGER.info(a);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/recieve?topic1=topic", String.class);
		return response;
	}	
	@GetMapping("/recieve")
	public List<String> recieve(@RequestParam("topic1") String topic1) {
		List<String> list = consumerService.MessageInTopic(topic1);
		  return list;
	
	}
}
