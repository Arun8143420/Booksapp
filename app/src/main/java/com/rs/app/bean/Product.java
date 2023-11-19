package com.rs.app.bean;

import java.io.File;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "product")
@Setter
@Getter
public class Product {
	@Id
	private String pId;

	private String bookTitle;

	private String author;
	private String description;
	private String language;
<<<<<<< HEAD
	private String category;
	private int pages;
	
	private File file;
	
=======
	@Field
	private String file;
	@Field
	private String uId;
	@Field
	private String category;

>>>>>>> b49da95494a4926f7ef824172672379c27eaacf3
	public Product() {
	}

	public Product(String bookTitle, String author, String description, String language, String file, String uId) {

		this.bookTitle = bookTitle;
		this.author = author;
		this.description = description;
		this.language = language;

	}

	@Override
	public String toString() {
		return String.format(
				"User[pId='%s',bookTitle='%s',author='%s',description='%s',language='%s',file='%s',uId='%s']", pId,
				bookTitle, author, description, language);
	}

}
