package com.rs.app.service;

import java.util.List;

import com.rs.app.bean.Product;
import com.rs.app.request.AddProductRequest;

public interface ProductService {

	public boolean addProduct(AddProductRequest request);

	List<Product> getProductByBookTitle(String bookTitle);

	List<Product> getProductByAuthor(String author);

	List<Product> getProductById(String pId);

	public List<Product> getSearch(String searchParam);

}
