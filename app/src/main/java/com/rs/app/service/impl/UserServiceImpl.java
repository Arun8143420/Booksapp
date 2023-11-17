package com.rs.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.app.bean.User;
import com.rs.app.repositories.UserRepository;
import com.rs.app.request.RegistrationRequest;
import com.rs.app.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	 UserRepository userRepository;
	
	private User setUser(RegistrationRequest request) {
		User user = new User();
		
		if(request != null) {
			user.setFirstName(request.getFirstName());
			user.setMiddleName(request.getMiddleName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setUsername(request.getUsername());
			user.setPassword(request.getPassword());
			user.setMobile(request.getMobile());
		}
		return user;
	}

	@Override
	public boolean registration(RegistrationRequest request) {
		
		User user = setUser(request);
		boolean isRegistred = false;
		try {
			User user1 = userRepository.save(user);
			isRegistred = true;
		}catch(Exception e) {
			
		}
		return isRegistred;
	}

	//Arun
}
