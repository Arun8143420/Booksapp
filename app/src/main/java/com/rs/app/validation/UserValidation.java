package com.rs.app.validation;

import java.util.Set;

import com.rs.app.request.GetUserIdRequest;
import com.rs.app.request.LoginRequest;
import com.rs.app.request.RegistrationRequest;

public interface UserValidation {

	Set<String> validateRegistrationRequest(RegistrationRequest request);

	Set<String> validateLoginRequest(LoginRequest request);

	Set<String> validateGetUser(GetUserIdRequest request);

}
