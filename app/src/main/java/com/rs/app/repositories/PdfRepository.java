package com.rs.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rs.app.bean.Pdf;

public interface PdfRepository extends MongoRepository<Pdf, String> {

}
