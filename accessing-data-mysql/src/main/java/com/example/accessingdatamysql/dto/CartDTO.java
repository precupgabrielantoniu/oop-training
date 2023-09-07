package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Set;

public class CartDTO implements Serializable {

    @JsonProperty("buyer_name")
    private String buyerName;

    @JsonProperty("products")
    private Set<ProductDTO> products;

    public static CartDTO toCartDTO(DisplayUserDTO userDTO, Set<ProductDTO> productDTOs){
        CartDTO cartDTO = new CartDTO();
        cartDTO.buyerName = userDTO.getName();
        cartDTO.products = productDTOs;
        return cartDTO;
    }
}
