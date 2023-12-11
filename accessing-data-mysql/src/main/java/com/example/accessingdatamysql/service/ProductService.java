package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.errorhandling.NoProductWithIdException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface ProductService {
    ProductDTO saveProduct(ProductDTO productDTO);
    Set<DisplayUserDTO> getBuyers(Integer productId) throws NoProductWithIdException;
    List<ProductDTO> getProductPageByPrice(Double price, Pageable pageable);
    Page<ProductDTO> getProductPageByCategory(String category, Pageable pageable);
}
