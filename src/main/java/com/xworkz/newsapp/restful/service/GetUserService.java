package com.xworkz.newsapp.restful.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.newsapp.restful.dao.UserDAO;
import com.xworkz.newsapp.restful.dto.UserDTO;

@Service
public class GetUserService {

	public static final Logger logger = LoggerFactory.getLogger(GetUserService.class.getName());

	@Autowired
	private UserDAO userDAO;

	public GetUserService() {
		logger.info("Created ::::::::::::::::: " + this.getClass().getSimpleName());
	}

	public UserDTO getUser(int id) {
		logger.info("Inside user service get user :::: ");

		try {

			UserDTO userDTO = userDAO.getOne(id);

			if (userDTO != null) {
				return userDTO;
			}
			return null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}

	public boolean saveUserService(UserDTO userDTO) {
		logger.info("Inside user service :::: ");
		try {
			UserDTO dto = userDAO.save(userDTO);
			if (dto != null) {
				return true;
			}
		} catch (Exception ex) {

		}
		return false;
	}

	public UserDTO getUserForLogin(String username) {
		logger.info("Inside user service for login :::: ");
		UserDTO response = null;
		try {
			response = userDAO.findByusername(username);
			logger.info("User details from db " + response);
			return response;
		} catch (Exception ex) {
			logger.info("Exception while getting user for login ");
		}
		return response;
	}
	
	public Long getUserCountByUsername(String username) {
		logger.info("Inside user service to get user count by username ");
		try {
			return userDAO.countByusername(username);
		} catch (Exception e) {
			logger.info("Exception while getting user count by username");
		}
		return 0L;
	}
}