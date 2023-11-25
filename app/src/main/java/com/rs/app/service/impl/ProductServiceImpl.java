package com.rs.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSUploadStream;
import com.rs.app.bean.Pdf;
import com.rs.app.bean.Product;
import com.rs.app.repositories.PdfRepository;
import com.rs.app.repositories.ProductRepository;
import com.rs.app.request.AddProductRequest;
import com.rs.app.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	PdfRepository pdfRepository;

	private Product setProduct(AddProductRequest request, MultipartFile file) throws FileNotFoundException {
		Product product = new Product();

		if (request != null) {
			product.setPId(request.getPId());
			product.setBookTitle(request.getBookTitle());
			product.setAuthor(request.getAuthor());
			product.setDescription(request.getDescription());
			product.setLanguage(request.getLanguage());
			product.setFile(file);
			product.setUId(request.getUId());

		}
		return product;
	}

	@Override
	public boolean addProduct(AddProductRequest request, MultipartFile file) {

		boolean isProductAdded = false;
		try {
			Product product = setProduct(request, file);
			productRepository.save(product);
			isProductAdded = true;
		} catch (Exception e) {

		}

		return isProductAdded;

	}

	@Override
	public List<Product> getProductByBookTitle(String bookTitle) {

		return productRepository.findByBookTitle(bookTitle);
	}

	@Override
	public List<Product> getProductByAuthor(String author) {

		return productRepository.findByAuthor(author);
	}

	@Override
	public List<Product> getProductById(String pId) {

		return productRepository.findBypId(pId);
	}

	public List<Product> getSearch(String searchParam) {

		List<Product> oProduct = productRepository
				.findByBookTitleContainingOrAuthorContainingOrLanguageContaining(searchParam, searchParam, searchParam);

		return oProduct;
	}

	@Override
	public boolean uploadFile(String file1) {

		String connectionString = "mongodb+srv://root:root@cluster0.fh3phoi.mongodb.net/db02?retryWrites=true&w=majority";

		try (MongoClient mongoClient = MongoClients.create(connectionString)) {
			MongoDatabase database = mongoClient.getDatabase("db02");

			GridFSBucket gridFSBucket = GridFSBuckets.create(database);

			File file = new File("C:\\Users\\Surya\\OneDrive\\Downloads\\" + file1);
			FileInputStream fileInputStream = new FileInputStream(file);

			GridFSUploadStream uploadStream = gridFSBucket.openUploadStream(file.getName());

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = fileInputStream.read(buffer)) != -1) {
				uploadStream.write(buffer, 0, bytesRead);
			}

			uploadStream.close();

			return true;
		} catch (IOException e) {
			return false;

		}

	}

	@Override
	public String uploadPdf(MultipartFile file) {
		try {
			Pdf pdf = new Pdf();
			pdf.setName(file.getOriginalFilename());
			pdf.setUploadedDate(new Date());
			pdf.setData(file.getBytes());
			pdfRepository.save(pdf);
			return "PDF file uploaded successfully";
		} catch (Exception e) {
			return "Upload is failed";
		}

	}
}
