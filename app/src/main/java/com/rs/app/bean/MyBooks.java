package com.rs.app.bean;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Mybooks")
@Getter
@Setter
public class MyBooks {
    
    @Id
    private String id;

    private String uId;

    private List<String> pIds;

    @DBRef
    private User user;

    // Getter and Setter for uId
    public String getUId() {
        return uId;
    }

    // Constructors, getters, setters
}
