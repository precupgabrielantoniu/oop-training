package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CarDTO implements Serializable {

    @JsonProperty("brand")
    String brand;

    @JsonProperty("stock")
    Integer stock;

    public static Car fromDTO(CarDTO carDTO){
        Car car = new Car();
        car.setBrand(carDTO.brand);
        car.setStock(carDTO.stock);
        return car;
    }

    public static CarDTO fromEntity(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.brand = car.getBrand();
        carDTO.stock = car.getStock();
        return carDTO;
    }
}
