package com.demo.chat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.chat.Model.Message;

@Repository
public interface MessageRepo extends JpaRepository<Message,Long> {
  @Query(value="select message from message ",nativeQuery=true)
  List<String> listMessage();
  
  @Query(value="select message from message inner join topic on message.topic_id=topic.id where name_topic=:nameTopic",nativeQuery=true)
  List<String> listMessageWithTopic(@Param("nameTopic")String nameTopic);
}
