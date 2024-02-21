package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CreateBookDTO;
import com.example.accessingdatamysql.dto.DisplayBookDTO;
import com.example.accessingdatamysql.errorhandling.NoBookWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;

public interface BookService {
    DisplayBookDTO saveBook(CreateBookDTO createBookDTO, Integer userId) throws NoUserWithIdException;
    DisplayBookDTO deleteBook(Integer id) throws NoBookWithIdException;
}
