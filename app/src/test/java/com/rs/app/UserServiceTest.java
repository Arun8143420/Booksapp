package com.rs.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rs.app.controllers.UserController;
import com.rs.app.request.RegistrationRequest;
import com.rs.app.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	public void successfulRegistration() {
		// Arrange
		RegistrationRequest request = new RegistrationRequest();

		request.setFirstName("kavya");
		request.setMiddleName("sri");
		request.setLastName("k");
		request.setEmail("kavya@12.com");
		request.setUsername("55");
		request.setPassword("66");
		request.setMobile("12345678");
		request.setUserType("user");
		boolean actual = userService.registration(request);

		// Act
		ResponseEntity<String> responseEntity = userController.registration(request);

		// Assert

		assertEquals(true, actual);
		assertEquals("User registered successfully", responseEntity.getBody());

	}

	@Test
	public void failureRegistration() {
		// Arrange
		RegistrationRequest request = new RegistrationRequest();

		request.setFirstName("kavya");
		request.setMiddleName("sri");
		request.setLastName("k");
		request.setUsername("55");
		request.setPassword("66");
		request.setMobile("12345678");
		when(userService.registration(request)).thenReturn(false);

		// Act
		ResponseEntity<String> responseEntity = userController.registration(request);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Email is already in use", responseEntity.getBody());

	}
}