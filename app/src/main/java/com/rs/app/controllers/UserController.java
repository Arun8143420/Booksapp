package com.rs.app.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.bean.MyBooks;
import com.rs.app.bean.Product;
import com.rs.app.bean.User;
import com.rs.app.request.AddMyBooksRequest;
import com.rs.app.request.GetMyBooksRequest;
import com.rs.app.request.GetUserIdRequest;
import com.rs.app.request.LoginRequest;
import com.rs.app.request.RegistrationRequest;
import com.rs.app.service.ProductService;
import com.rs.app.service.UserService;
import com.rs.app.validation.UserValidation;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserValidation userValidation;

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@PostMapping("/users")
	public ResponseEntity<String> registration(@RequestBody RegistrationRequest request) {

		Set<String> errorMessages = userValidation.validateRegistrationRequest(request);

		boolean isRegistersd = false;
		if (errorMessages.isEmpty() && errorMessages != null) {
			isRegistersd = userService.registration(request);
			if (isRegistersd) {
				return new ResponseEntity<String>("User registred successfully", HttpStatus.OK);

			}
		}
		return new ResponseEntity<>("Registration got failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/login")
	public ResponseEntity<String> login(@ModelAttribute LoginRequest request) {

		Set<String> errorMessages = userValidation.validateLoginRequest(request);

		if (errorMessages.isEmpty() && errorMessages != null) {
			User user = userService.login(request);
			return new ResponseEntity<String>("User is present", HttpStatus.OK);

		}
		return new ResponseEntity<>("User is not present", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getUser/{id}")
	public ResponseEntity<String> getUser(@PathVariable String id) {
		GetUserIdRequest request = new GetUserIdRequest();
		request.setId(id);
		Set<String> errorMessages = userValidation.validateGetUser(request);

		if (errorMessages.isEmpty() && errorMessages != null) {
			userService.getUser(request);
			return new ResponseEntity<String>("User is present", HttpStatus.OK);

		}
		return new ResponseEntity<>("User is not present", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/*
	 * @PutMapping("/update") public ResponseEntity<String> updateUser(@RequestBody
	 * UpdateUserRequest request){
	 * 
	 * }
	 */
	@PutMapping("/addMyBooks/{id}")
	public ResponseEntity<String> addMyBooks(@RequestBody AddMyBooksRequest request) {

		GetMyBooksRequest uId = new GetMyBooksRequest();
		uId.setUId(request.getUId());
		
		List<MyBooks> myBooksList = userService.getMyBooks(uId);
		List<String> pIds = myBooksList.get(0).getPIds();
		pIds.add(request.getPIds().get(0));
		if (request.getId() != null && !myBooksList.isEmpty()) {
			request.setPIds(pIds);
		}

		if (!request.equals(null)) {

			userService.addMyBooks(request);
			return new ResponseEntity<String>("Added successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>("adding failed", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/getMyBooks")
	public ResponseEntity<List<Product>> getMyBooks(@RequestParam String uId) {
		GetMyBooksRequest request = new GetMyBooksRequest();
		request.setUId(uId);

		List<MyBooks> myBooksList = userService.getMyBooks(request);
		List<Product> myBooks = new LinkedList<>();
		List<String> pids = myBooksList.get(0).getPIds();
		if (myBooksList != null && !myBooksList.isEmpty()) {
			for (String pid : pids) {
				List<Product> books = productService.getProductById(pid);
				if (books != null) {
					myBooks.add(books.get(0));
				}
			}
			return new ResponseEntity<>(myBooks, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
