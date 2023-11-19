package com.rs.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.app.bean.Product;
import com.rs.app.repositories.ProductRepository;
import com.rs.app.request.AddProductRequest;
import com.rs.app.service.ProductService;

@Service
public class ProductServiceImpl  implements ProductService{

	@Autowired
	
	 ProductRepository productRepository;
	
	private Product setProduct(AddProductRequest request) {
		Product product = new Product();
		
		if(request != null) {
			
			product.setPId(request.getPId());
			product.setBookTitle(request.getBookTitle());
			product.setAuthor(request.getAuthor());
			product.setDescription(request.getDescription());
			product.setLanguage(request.getLanguage());
			product.setFile(request.getFile());
			
			
		}
		return product;
	}
	
	@Override
	public boolean addProduct(AddProductRequest request) {
		
		Product product = setProduct(request);
		boolean isProductAdded = false;
		try {
			Product product1 = productRepository.save(product);
			isProductAdded = true;
		}catch(Exception e) {
			
		}
		
		return isProductAdded;
		
		
		
	}
	

}
