package com.xworkz.newsapp.restful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xworkz.newsapp.restful.dto.UserDTO;

@Repository
public interface UserDAO extends JpaRepository<UserDTO, Integer> {

	public UserDTO findByusername(String username);
	
	public Long countByusername(String username);
	
}
