package com.rs.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.repositories.ProductRepository;
import com.rs.app.request.AddProductRequest;
import com.rs.app.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	public final ProductRepository ProductRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	public ProductController(ProductRepository ProductRepository) {
		this.ProductRepository = ProductRepository;
	}
	
	@PostMapping("/addProduct")
	public ResponseEntity<String> addProduct(@RequestBody AddProductRequest request){
		
		boolean isAddProduct = false;
		
		isAddProduct = productService.addProduct(request);
		
		if(isAddProduct) {
			
			return new ResponseEntity<String>("product added successfully", HttpStatus.OK);
			
		}else {
			
			return new ResponseEntity<String>("product adding is failed",HttpStatus.PRECONDITION_FAILED);
		}
		
	}

}
