package com.rs.app;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rs.app.bean.User;
import com.rs.app.repositories.MyBooksRepository;
import com.rs.app.repositories.UserRepository;
import com.rs.app.request.LoginRequest;
import com.rs.app.service.impl.UserServiceImpl;
import com.rs.app.validation.impl.UserValidationImpl;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private MyBooksRepository myBooksRepository;

	@InjectMocks
	UserValidationImpl userValidation;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void TestLoginSuccess() {
		LoginRequest request = new LoginRequest();
		request.setUsername("55");
		request.setPassword("66");

		request.setUserType("user");

		Set<String> errorMessages = userValidation.validateLoginRequest(request);

		User user = userService.login(request);

		assertEquals(true, errorMessages.isEmpty());
		assertEquals(null, user);

	}

	@Test
	public void TestLoginFailure() {
		LoginRequest request = new LoginRequest();
		request.setUsername("55");

		request.setUserType("user");

		Set<String> errorMessages = userValidation.validateLoginRequest(request);

		User user = new User();
		if (errorMessages.isEmpty()) {
			user = userService.login(request);
		}
		assertEquals(false, errorMessages.isEmpty());
		assertNotEquals(request.getUsername(), user.getUsername());

	}

}
