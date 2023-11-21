package com.rs.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rs.app.bean.MyBooks;

@Repository
public interface MyBooksRepository extends MongoRepository<MyBooks, String> {

	List<MyBooks> findByuId(String uId);

	/*
	 * @Query("{ 'uId' : ?0 }") List<MyBooks> findByuId(String uId);
	 */
}
