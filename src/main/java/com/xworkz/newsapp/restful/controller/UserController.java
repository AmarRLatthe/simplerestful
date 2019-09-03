package com.xworkz.newsapp.restful.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xworkz.newsapp.restful.dto.ResponseDTO;
import com.xworkz.newsapp.restful.dto.UserDTO;
import com.xworkz.newsapp.restful.service.GetUserService;

@RestController
@RequestMapping(value = "/guest")
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());

	@Autowired
	private GetUserService getUserService;

	public UserController() {
		logger.info("Created :::::::::::: " + this.getClass().getSimpleName());
	}

	@GetMapping(value = "/greet/{id}")
	public ResponseEntity<UserDTO> getUser(@PathVariable int id) {
		logger.info("Inside get user controller :::::::: ");

		try {
			UserDTO userDTO = getUserService.getUser(id);
			if (userDTO != null) {
				return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
			}
		} catch (Exception e) {

		}
		return null;
	}

	@PostMapping(value = "/save")
	public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO) {
		logger.info("Inside save user controller :::::::::::::: ");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			boolean response = getUserService.saveUserService(userDTO);
			if (response) {
				responseDTO.setDescription("User inserted successfully..");
				responseDTO.setResponse("Success");
				return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
			} else {

			}
		} catch (Exception e) {
			logger.info("Exception while inserting user.... " + e.getMessage());
		}
		return null;
	}

	@PostMapping(value = "/getUser")
	public ResponseEntity<ResponseDTO> getUserForLogin(@RequestBody UserDTO userDTO) {
		logger.info("Inside get user for login .... ");
		ResponseDTO responseDTO = new ResponseDTO();
		try {
			UserDTO response = getUserService.getUserForLogin(userDTO.getUsername());
			if (response != null && response.getUsername().equals(userDTO.getUsername())
					&& response.getPassword().equals(userDTO.getPassword())) {
				responseDTO.setResponse("Success");
				responseDTO.setDescription("Valid user");
			} else {
				responseDTO.setResponse("Unsuccessful");
				responseDTO.setDescription("Not a valid user");
			}
		} catch (Exception ex) {

		}
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/countByUser")
	public ResponseEntity<ResponseDTO> countByUsername(@RequestBody UserDTO userDTO) {
		logger.info("Inside get user count by username :::::::::: " + userDTO.getUsername());
		ResponseDTO responseDTO = new ResponseDTO();
		Long userCount = 0L;
		try {
			userCount = getUserService.getUserCountByUsername(userDTO.getUsername());
			if(userCount == 1) {
				responseDTO.setResponse("Invalid");
				responseDTO.setDescription("User Valid user");
				return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			} else {
				responseDTO.setResponse("Valid");
				responseDTO.setDescription("Not a valid user");
				return new ResponseEntity<>(responseDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseDTO.setResponse("Error");
			responseDTO.setDescription("Error while getting user count");
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);
		}

	}
}