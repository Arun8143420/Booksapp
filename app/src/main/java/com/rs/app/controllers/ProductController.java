package com.rs.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.repositories.ProductRepository;

@RestController
@RequestMapping("/api")
public class ProductController {

	public final ProductRepository ProductRepository;

	@Autowired
	public ProductController(ProductRepository ProductRepository) {
		this.ProductRepository = ProductRepository;
	}

}
