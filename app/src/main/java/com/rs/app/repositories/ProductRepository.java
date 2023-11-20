package com.rs.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rs.app.bean.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findByAuthor(String author);
	
	List<Product> findByBookTitle(String title);
	
	List<Product> findByDescription(String description);
	
	List<Product> findByLanguage(String language);
	
	List<Product> findBypId(String pid);
	
	List<Product> findByuId(String uid);
}
