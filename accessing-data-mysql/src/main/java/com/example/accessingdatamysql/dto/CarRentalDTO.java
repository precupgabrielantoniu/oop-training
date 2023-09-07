package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Car;
import com.example.accessingdatamysql.entity.CarRental;
import com.example.accessingdatamysql.entity.User;
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

    public static CarRental fromDTO(CarRentalDTO carRentalDTO){
        CarRental carRental = new CarRental();
        carRental.setCar(CarDTO.fromDTO(carRentalDTO.carDTO));
        carRental.setUser(DisplayUserDTO.fromDTO(carRentalDTO.userDTO));
        carRental.setRentalDate(carRentalDTO.rentalDate);
        return carRental;
    }

    public static CarRentalDTO fromEntity(CarRental carRental){
        CarRentalDTO carRentalDTO = new CarRentalDTO();
        carRentalDTO.carDTO = CarDTO.fromEntity(carRental.getCar());
        carRentalDTO.userDTO = DisplayUserDTO.fromEntity(carRental.getUser());
        carRentalDTO.rentalDate = carRental.getRentalDate();
        return carRentalDTO;
    }
}
