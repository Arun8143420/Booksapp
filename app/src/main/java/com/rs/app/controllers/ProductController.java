package com.rs.app.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rs.app.bean.Product;
import com.rs.app.repositories.ProductRepository;
import com.rs.app.request.AddProductRequest;
import com.rs.app.service.ProductService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Validated
public class ProductController {

	public final ProductRepository ProductRepository;

	@Autowired
	ProductService productService;

	@Autowired
	public ProductController(ProductRepository ProductRepository) {
		this.ProductRepository = ProductRepository;
	}

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity<String> addProduct(@RequestBody @Valid AddProductRequest request,
			@RequestPart("file") MultipartFile file) {

		boolean isAddProduct = false;

		isAddProduct = productService.addProduct(request, file);

		if (isAddProduct) {

			return new ResponseEntity<String>("product added successfully", HttpStatus.OK);

		} else {

			return new ResponseEntity<String>("product adding is failed", HttpStatus.PRECONDITION_FAILED);
		}

	}

	@PostMapping("/uploadPdf")
	@ApiOperation(value = "Upload PDF file to MongoDB", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "file", value = "PDF file", required = true, dataType = "file", paramType = "form") })
	public ResponseEntity<String> uploadPdf(@RequestPart("file") MultipartFile file) throws IOException {
		try {
			String result = productService.uploadPdf(file);
			return ResponseEntity.ok(result); // HTTP 200 OK
		} catch (Exception e) {
			// Handle other exceptions
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File upload failed: " + e.getMessage());
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

	@GetMapping("/search")
	public ResponseEntity<List<Product>> getSearch(@RequestParam String searchParam) {

		List<Product> products = productService.getSearch(searchParam);

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);

	}

}
