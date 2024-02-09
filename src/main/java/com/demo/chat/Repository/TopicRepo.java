package com.demo.chat.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.chat.Model.TopicMessage;
@Repository
public interface TopicRepo extends JpaRepository<TopicMessage,Integer>{
    @Query(value="select * from topic where name_topic =:nametopic",nativeQuery=true)
    TopicMessage findByName(@Param("nametopic") String nametopic);
    
    @Query(value="select * from topic",nativeQuery=true)
    List<String> findAllTopic();
}
