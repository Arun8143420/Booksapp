package com.rs.app;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rs.app.repositories.MyBooksRepository;
import com.rs.app.repositories.UserRepository;
import com.rs.app.request.RegistrationRequest;
import com.rs.app.service.impl.UserServiceImpl;
import com.rs.app.validation.impl.UserValidationImpl;

@ExtendWith(MockitoExtension.class)
public class RegistrationTestCase {

	@Mock
	private UserRepository userRepository;

	@Mock
	private MyBooksRepository myBooksRepository;

	@InjectMocks
	UserValidationImpl userValidation;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	public void registrationSuccess() {
		RegistrationRequest request = new RegistrationRequest();
		request.setFirstName("kavya");
		request.setMiddleName("sri");
		request.setLastName("k");
		request.setEmail("kavya@12.com");
		request.setUsername("55");
		request.setPassword("66");
		request.setMobile("12345678");
		request.setUserType("user");

		Set<String> errorMessages = userValidation.validateRegistrationRequest(request);

		boolean actual = userService.registration(request);

		assertEquals(true, errorMessages.isEmpty());
		assertEquals(true, actual);

	}

	@Test
	public void registrationFailure() {
		RegistrationRequest request = new RegistrationRequest();
		request.setMiddleName("sri");
		request.setLastName("k");
		request.setEmail("kavya@12.com");
		request.setPassword("66");
		request.setMobile("12345678");

		Set<String> errorMessages = userValidation.validateRegistrationRequest(request);
		boolean actual = false;
		if (errorMessages.isEmpty()) {
			actual = userService.registration(request);
		}
		assertEquals(false, errorMessages.isEmpty());
		assertEquals(false, actual);

	}

}
