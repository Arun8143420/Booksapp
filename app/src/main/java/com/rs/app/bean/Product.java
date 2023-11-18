package com.rs.app.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "product")
@Setter
@Getter
public class Product {
	@Id
	private String pId;

	@Field
	private String bookTitle;

	@Field
	private String author;
	@Field
	private String description;
	@Field
	private String language;
	@Field
	private String file;
	@Field
	private String uId;
	@Field
	private String category;

	public Product() {
	}

	public Product(String bookTitle, String author, String description, String language, String file, String uId) {

		this.bookTitle = bookTitle;
		this.author = author;
		this.description = description;
		this.language = language;
		this.file = file;
		this.uId = uId;

	}

	@Override
	public String toString() {
		return String.format(
				"User[pId='%s',bookTitle='%s',author='%s',description='%s',language='%s',file='%s',uId='%s']", pId,
				bookTitle, author, description, language, file, uId);
	}

}
