package com.demo.chat.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.chat.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

	@Query(value="select * from user where id =:uid",nativeQuery=true)
	public User findUserById(@Param("uid") int  uid);
	
	@Query(value="select * from user ",nativeQuery=true)
	public List<User> listUser();
	
	@Query(value="select * from user where username=:userName",nativeQuery=true)
	public User findByUsername(@Param("userName")String userName);
}
