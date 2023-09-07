package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CarRentalDTO implements Serializable {

    @JsonProperty("user")
    DisplayUserDTO userDTO;

    @JsonProperty("car")
    CarDTO carDTO;

    @JsonProperty("rental-date")
    Date rentalDate;
}
