package com.rs.app.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSubscriptionRequest {

	private String username;
	private String email;
	private String password;
	private String firstName;
	private String middleName;
	private String lastName;
	private String mobile;
	private String subscriptionActive;
	private String subscriptionExpirationDate;

}
