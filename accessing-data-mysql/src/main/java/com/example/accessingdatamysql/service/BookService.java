package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.BookDTO;
import com.example.accessingdatamysql.errorhandling.NoBookWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;

public interface BookService {
    BookDTO saveBook(BookDTO bookDTO, Integer userId) throws NoUserWithIdException;
    BookDTO deleteBook(Integer id) throws NoBookWithIdException;
}