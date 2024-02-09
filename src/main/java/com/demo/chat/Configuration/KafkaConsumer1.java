package com.demo.chat.Configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.demo.chat.Repository.TopicRepo;



@Configuration
public class KafkaConsumer1 {
    @Autowired
    private TopicRepo topicRepo;
  //  List<String> topics = topicRepo.findAllTopic();
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		//so luong lang nghe tuong ung voi so topic
		factory.setConcurrency(5);
		return factory;
	}
	
	@Bean
    public ConsumerFactory consumerFactory() {
		return new DefaultKafkaConsumerFactory(mapConfig());
	}
	
	
	@Bean
	public Map<String,Object> mapConfig(){
		Map<String,Object> map = new HashMap<>();
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		map.put(ConsumerConfig.GROUP_ID_CONFIG,"your_group_id");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		return map;
	}
//	@Bean
//	public void setupDynamicListeners() {
//		ConcurrentKafkaListenerContainerFactory<String,String> factory =
//				kafkaListenerContainerFactory();
//		     for(String topic:topics) {
//		   	// ContainerProperties containerProperties = new ContainerProperties(topic);
//		    			 
//		    	 factory.createContainer(topic);
//		     }
//	}
}
