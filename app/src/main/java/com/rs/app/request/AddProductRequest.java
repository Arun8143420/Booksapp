package com.rs.app.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductRequest {
	
	private String pId;
	
    @NotBlank(message = "Booktitle is required")
	private String bookTitle;
	
    @NotNull(message = "Author cannot be null")
	private String author;
	
    @NotNull(message = "descrption cannot be null")
	private String description;
	
    @NotNull(message = "language cannot be null")
	private String language;
	
    @NotBlank(message = "file name is required")
	private String file;
	
    @NotBlank(message = "uId name is required")
	private String uId;

}
