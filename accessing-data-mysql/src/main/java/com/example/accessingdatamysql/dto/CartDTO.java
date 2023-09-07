package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
public class CartDTO implements Serializable {

    @JsonProperty("buyer_name")
    private String buyerName;

    @JsonProperty("products")
    private Set<ProductDTO> products;
}
