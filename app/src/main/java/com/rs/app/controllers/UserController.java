package com.rs.app.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.bean.User;
import com.rs.app.request.GetUserIdRequest;
import com.rs.app.request.LoginRequest;
import com.rs.app.request.RegistrationRequest;
import com.rs.app.service.UserService;
import com.rs.app.validation.UserValidation;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserValidation userValidation;

	@Autowired
	UserService userService;

	@PostMapping("/users")
	public ResponseEntity<String> registration(@RequestBody RegistrationRequest request) {

		Set<String> errorMessages = userValidation.validateRegistrationRequest(request);

		boolean isRegistersd = false;
		if (errorMessages.isEmpty() && errorMessages != null) {
			isRegistersd = userService.registration(request);
			if (isRegistersd) {
				return new ResponseEntity<String>("User registred successfully", HttpStatus.OK);

			}
		}
		return new ResponseEntity<>("Registration got failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@ModelAttribute LoginRequest request) {

		Set<String> errorMessages = userValidation.validateLoginRequest(request);

		if (errorMessages.isEmpty() && errorMessages != null) {
			User user = userService.login(request);
			return new ResponseEntity<String>("User is present", HttpStatus.OK);

		}
		return new ResponseEntity<>("User is not present", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<String> getUser(@PathVariable String id) {
		GetUserIdRequest request = new GetUserIdRequest();
		request.setId(id);
		Set<String> errorMessages = userValidation.validateGetUser(request);
		
		if (errorMessages.isEmpty() && errorMessages != null) {
			userService.getUser(request);
			return new ResponseEntity<String>("User is present", HttpStatus.OK);

		}
		return new ResponseEntity<>("User is not present", HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	/*
	 * @PutMapping("/update") public ResponseEntity<String> updateUser(@RequestBody
	 * UpdateUserRequest request){
	 * 
	 * }
	 */

}
