package com.rs.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rs.app.bean.User;
import com.rs.app.request.RegistrationRequest;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	List<User> findByUsernameAndPassword(String userName, String password);

	
}
