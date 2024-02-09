package com.demo.chat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.chat.Model.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {

	
}
