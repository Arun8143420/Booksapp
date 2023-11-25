package com.rs.app.product;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rs.app.repositories.ProductRepository;
import com.rs.app.request.AddProductRequest;
import com.rs.app.service.impl.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AddProductTest {

	@Mock
	public ProductRepository ProductRepository;

	@InjectMocks
	public ProductServiceImpl productService;

	@Test
	public void TestAddProductSuccess() {
		AddProductRequest request = new AddProductRequest();
		request.setBookTitle("book");
		request.setAuthor("author");
		request.setDescription("history");
		request.setLanguage("eng");
		request.setUId("1234567");

		//boolean isAdded = productService.addProduct(request);

		//assertEquals(true, isAdded);

	}

}
