package com.rs.app.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

	private String firstName;
	private String middleName;
	private String lastName;
	
    @Email(message = "Invalid email format")
	private String email;

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;
    
    @Size(min = 2, max = 20, message = "Password must be between 2 and 20 characters")
	private String password;

	private String mobile;

	private String userType;
}
