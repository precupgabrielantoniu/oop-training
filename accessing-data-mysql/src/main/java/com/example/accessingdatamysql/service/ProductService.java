package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;

import java.util.Set;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    Set<DisplayUserDTO> getBuyers(Integer productId) throws NoProductWithIdException;
}
