package com.demo.chat.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.demo.chat.Repository.MessageRepo;
import com.demo.chat.Repository.UserRepo;

@Service
public class ConsumerService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private MessageRepo messageRepo;
//	List<String > list = new ArrayList<>();
//	String recieveMessage;
//	@KafkaListener(topics="message",groupId="yeuanh",containerFactory="kafkaListenerContainerFactory")
//	void listen(List<ConsumerRecord<String,String>>messages) {
//		List<String > list = new ArrayList<>();
//		for(ConsumerRecord<String,String> message:messages) {
//			//System.out.println("recieve message from kafka : "+message.value());
//			
//			list.add(message.value());
//			}
//		
//	
//		
//	}
//	public List<String> recieve() {
//		return list;
//	}
	
////	public List<Message> list(){
////		return messageRepo.listMessage();
//	
//	 @Autowired
//	   private KafkaTemplate<String,String> kafkaTemplate;
   private static final String bootstrap_server="localhost:9092";
   private static final String url="http://localhost:8081/recieve";
   private  KafkaTemplate<String, String> kafkaTemplate;

   public void KafkaMessageReceiver(KafkaTemplate<String, String> kafkaTemplate) {
       this.kafkaTemplate = kafkaTemplate;
   }
   

//   public List<String> MessageInTopic(String topic) {
//	  
//	 Properties prop = new Properties();
//	   prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
//	   prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
//	   prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//	   prop.put(ConsumerConfig.GROUP_ID_CONFIG, topic);
//	   List<String> message = messageRepo.listMessage();
//	   try(KafkaConsumer<String,String> con = new KafkaConsumer<>(prop)){
//		   con.subscribe(Collections.singleton(topic));		
//		   
//			   ConsumerRecords<String,String > record = con.poll(Duration.ofMillis(100));
//			   for(ConsumerRecord<String,String> r : record) {
//				   message.add(r.value());
//				   }
//		   
//			   
//		   
//	   }catch(Exception e) {
//		   e.printStackTrace();
//	   }
//	 
//	return message;
//   }
   
   //các này sử dụng @KafkaListener
   @Autowired
   private ConsumerFactory<String,String> consumerFactory;
//   private final ConsumerFactory<String, String> consumerFactory;
//
//   public KafkaMessageService(ConsumerFactory<String, String> consumerFactory) {
//       this.consumerFactory = consumerFactory;
//   }
   public List<String> MessageInTopic(String topic) {
       Properties consumerProps = new Properties();
       consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
    // consumerProps.put(ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG, topic);
       consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG,"your_group_id");
       consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
       consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

       Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(consumerProps);
       kafkaConsumer.subscribe(Collections.singletonList(topic));

       List<String> messages = new ArrayList<>();
       // Poll for new messages
       ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
       for (ConsumerRecord<String, String> record : consumerRecords) {
           messages.add(record.value());
       }

     //  kafkaConsumer.close();
       return messages;
   }

 
   public List<String> allMessageWithTopic(String topic){
	   List<String> list = messageRepo.listMessageWithTopic(topic);
	   return list;
   }
//   public void recieveMessage(String topic) {
//	   ListenableFuture<ConsumerRecord<String,String>> future =
//			    kafkaTemplate.receive(topic);
//	   future.addCallback(result->{
//		   String message=result.value();
//	   },ex->{
//		   ex.printStackTrace();
//	   });
//   }
}
