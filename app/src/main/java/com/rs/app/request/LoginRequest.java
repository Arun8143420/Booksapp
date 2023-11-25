package com.rs.app.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
	
	@NotNull(message = "username should not null")
	private String username;
	
    @Size(min = 2, max = 20, message = "Password must be between 2 and 20 characters")
	private String password;
    
	public String UserType;
}
