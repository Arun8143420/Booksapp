package com.rs.app.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "pdfs")
public class Pdf {
	
	@Id
	private String id;
	private String name;
	private Date uploadedDate;
	private byte[] data;

}