package com.rs.app.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
