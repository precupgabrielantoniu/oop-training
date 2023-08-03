package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {
}
