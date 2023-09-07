package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ProductDTO implements Serializable {

    @JsonProperty("category")
    private String category;

    @JsonProperty("company")
    private String company;

    @JsonProperty("price")
    private Double price;
}
