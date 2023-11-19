package com.rs.app.service;

import com.rs.app.bean.User;
import com.rs.app.request.GetUserIdRequest;
import com.rs.app.request.LoginRequest;
import com.rs.app.request.RegistrationRequest;

public interface UserService {

	public boolean registration(RegistrationRequest request);

	public User login(LoginRequest request);

	public User getUser(GetUserIdRequest request);

	
	//Arun
	
	//1211
}
