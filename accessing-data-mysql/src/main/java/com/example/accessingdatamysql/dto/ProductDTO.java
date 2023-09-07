package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ProductDTO implements Serializable {

    @JsonProperty("category")
    private String category;

    @JsonProperty("company")
    private String company;

    @JsonProperty("price")
    private Double price;

    public static Product fromDTO(ProductDTO productDTO){
        Product product = new Product();
        product.setCategory(productDTO.category);
        product.setCompany(productDTO.company);
        product.setPrice(productDTO.price);
        return product;
    }

    public static ProductDTO fromEntity(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.category = product.getCategory();
        productDTO.company = product.getCompany();
        productDTO.price = product.getPrice();
        return productDTO;
    }
}
