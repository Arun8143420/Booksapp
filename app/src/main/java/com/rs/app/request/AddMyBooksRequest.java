package com.rs.app.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AddMyBooksRequest {
	
	private String id;
	
	private String uId;
	
	private List<String> pIds;
}
