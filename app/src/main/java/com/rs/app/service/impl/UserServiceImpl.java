package com.rs.app.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.app.bean.MyBooks;
import com.rs.app.bean.User;
import com.rs.app.repositories.MyBooksRepository;
import com.rs.app.repositories.UserRepository;
import com.rs.app.request.AddMyBooksRequest;
import com.rs.app.request.GetMyBooksRequest;
import com.rs.app.request.GetUserIdRequest;
import com.rs.app.request.LoginRequest;
import com.rs.app.request.RegistrationRequest;
import com.rs.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MyBooksRepository myBooksRepository;

	private User setUser(RegistrationRequest request) {
		User user = new User();

		if (request != null) {
			user.setFirstName(request.getFirstName());
			user.setMiddleName(request.getMiddleName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setUsername(request.getUsername());
			user.setPassword(request.getPassword());
			user.setMobile(request.getMobile());
		}
		return user;
	}

	private MyBooks setMyBooks(AddMyBooksRequest request) {
		MyBooks myBooks = new MyBooks();

		myBooks.setUId(request.getUId());
		myBooks.setPIds(request.getPIds());
		if (request.getId() != null) {
			myBooks.setId(request.getId());
		}
		return myBooks;
	}

	@Override
	public boolean registration(RegistrationRequest request) {

		User user = setUser(request);
		boolean isRegistred = false;
		try {
			userRepository.save(user);
			isRegistred = true;
		} catch (Exception e) {

		}
		return isRegistred;
	}

	@Override
	public User login(LoginRequest request) {

		User user = null;
		List<User> users = userRepository.findByUsernameAndPasswordAndUserType(request.getUsername(),
				request.getPassword(), request.getUserType());
		if (users != null && !users.isEmpty()) {
			user = users.get(0);
		}
		return user;
	}

	@Override
	public User getUser(GetUserIdRequest request) {
		Optional<User> oUser = userRepository.findById(request.getId());
		User user = oUser.get();
		return user;
	}

	@Override
	public boolean addMyBooks(AddMyBooksRequest request) {
		MyBooks myBooks = setMyBooks(request);
		try {

			myBooksRepository.save(myBooks);
			return true;
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public List<MyBooks> getMyBooks(GetMyBooksRequest request) {

		if (request == null || request.getUId() == null || request.getUId().isEmpty()) {

			return Collections.emptyList();
		}
		List<MyBooks> myBooksList = myBooksRepository.findByuId(request.getUId());

		return myBooksList != null ? myBooksList : Collections.emptyList();
	}

}
