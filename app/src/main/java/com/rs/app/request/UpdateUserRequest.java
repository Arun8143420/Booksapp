package com.rs.app.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

	private String firstName;
	private String middleName;
	private String lastName;

	private String email;

	private String username;
	private String password;

	private String mobile;
	
	private String premiumSubscription;

}
