package com.rs.app.request;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {

	private String pId;

	private String bookTitle;

	private String author;

	private String description;

	private String language;

	private File file;

	private String uId;

}
