package com.rs.app.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document
@Setter
@Getter
public class User {
	@Id
	private String id;

	@Field
	private String firstName;

	@Field
	private String middleName;
	@Field
	private String lastName;
	@Field
	private String email;
	@Field
	private String username;
	@Field
	private String password;

	@Field
	private String mobile;

	@Field
	private String userType;

	public User() {
	}

	public User(String firstName, String middleName, String lastName, String email, String username, String password,
			String mobile, String userType) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.userType = userType;

	}

	@Override
	public String toString() {
		return String.format(
				"User[id='%s',firstName='%s',middleName='%s',lastName='%s',email='%s',username='%s',password='%s',mobile='%s',userType='%s']",
				id, firstName, middleName, lastName, email, username, password, mobile, userType);
	}

}
