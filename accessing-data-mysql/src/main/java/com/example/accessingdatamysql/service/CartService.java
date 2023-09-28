package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CartDTO;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;

import java.io.FileNotFoundException;

public interface CartService {
    CartDTO addProductToUser(Integer productId, Integer userId) throws NoUserWithIdException, NoProductWithIdException, FileNotFoundException;
}
