package com.rs.app.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rs.app.bean.MyBooks;
import com.rs.app.request.GetMyBooksRequest;

@Repository
public interface MyBooksRepository extends MongoRepository<MyBooks, String> {

  //  List<MyBooks> findByuId(GetMyBooksRequest request);

    @Query("{ 'uId' : ?0 }")
    List<MyBooks> findByuId(String uId);
}
