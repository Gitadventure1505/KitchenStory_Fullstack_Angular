package com.in.Kitchenstory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.in.Kitchenstory.pojo.User;

public interface UserDao extends JpaRepository<User, Integer> 
{

	//User findByEmail(@Param("email") String email);
	User findByEmail(@Param("email") String email);
}
