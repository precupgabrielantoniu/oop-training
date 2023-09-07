package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CarDTO implements Serializable {

    @JsonProperty("brand")
    String brand;

    @JsonProperty("stock")
    Integer stock;
}
