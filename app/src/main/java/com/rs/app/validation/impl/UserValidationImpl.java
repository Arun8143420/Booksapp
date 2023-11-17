
package com.rs.app.validation.impl;

import java.util.LinkedHashSet;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.rs.app.request.RegistrationRequest;
import com.rs.app.validation.UserValidation;

//validations

@Component
public class UserValidationImpl implements UserValidation {

	private Set<String> validate(Set<String> errorMessage, Object value, String fieldName) {
		if (StringUtils.isEmpty(value)) {
			errorMessage.add(fieldName + " should not be empty");
		} else if ((value instanceof Integer && Integer.parseInt(value.toString()) <= 0)
				|| (value instanceof Float && Float.parseFloat(value.toString()) <= 0)) {
			errorMessage.add(fieldName + " should not be Zero");
		}

		return errorMessage;
	}

	@Override
	public Set<String> validateRegistrationRequest(RegistrationRequest request) {
		Set<String> errorMessages = new LinkedHashSet<String>();

		errorMessages = validate(errorMessages, request.getFirstName(), "FirstName");
		errorMessages = validate(errorMessages, request.getLastName(), "LastName");
		errorMessages = validate(errorMessages, request.getEmail(), "Email");
		errorMessages = validate(errorMessages, request.getUsername(), "UserName");
		errorMessages = validate(errorMessages, request.getPassword(), "Password");
		errorMessages = validate(errorMessages, request.getMobile(), "Mobile");
		errorMessages = validate(errorMessages, request.getUserType(), "UserType");

		return errorMessages;
	}

}

//system