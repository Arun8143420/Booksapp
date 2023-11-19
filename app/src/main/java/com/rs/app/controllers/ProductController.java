package com.rs.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
>>>>>>> b49da95494a4926f7ef824172672379c27eaacf3
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.bean.Product;
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
<<<<<<< HEAD
	public ResponseEntity<String> addProduct(@ModelAttribute AddProductRequest request){
		
=======
	public ResponseEntity<String> addProduct(@RequestBody AddProductRequest request) {

>>>>>>> b49da95494a4926f7ef824172672379c27eaacf3
		boolean isAddProduct = false;

		isAddProduct = productService.addProduct(request);

		if (isAddProduct) {

			return new ResponseEntity<String>("product added successfully", HttpStatus.OK);

		} else {

			return new ResponseEntity<String>("product adding is failed", HttpStatus.PRECONDITION_FAILED);
		}

	}

	@GetMapping("/product/{bookTitle}")
	public ResponseEntity<Product> getProductByBookTitle(@PathVariable String bookTitle) {

		List<Product> products = ProductRepository.findByBookTitle(bookTitle);

		if (!products.isEmpty() && products != null) {

			return new ResponseEntity<>(products.get(0), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/book/{author}")
	public ResponseEntity<Product> getProductByAuthor(@PathVariable String author) {

		List<Product> products = ProductRepository.findByAuthor(author);

		if (!products.isEmpty() && products != null) {

			return new ResponseEntity<>(products.get(0), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		}
	}

}
