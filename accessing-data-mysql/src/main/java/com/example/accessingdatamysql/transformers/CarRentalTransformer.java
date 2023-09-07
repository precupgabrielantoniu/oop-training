package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.CarRentalDTO;
import com.example.accessingdatamysql.entity.CarRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarRentalTransformer implements Transformer<CarRental, CarRentalDTO> {

    @Autowired
    private DisplayUserTransformer displayUserTransformer;

    @Autowired
    private CarTransformer carTransformer;

    public CarRental fromDTO(CarRentalDTO carRentalDTO){
        CarRental carRental = new CarRental();
        carRental.setCar(carTransformer.fromDTO(carRentalDTO.getCarDTO()));
        carRental.setUser(displayUserTransformer.fromDTO(carRentalDTO.getUserDTO()));
        carRental.setRentalDate(carRentalDTO.getRentalDate());
        return carRental;
    }

    public CarRentalDTO fromEntity(CarRental carRental){
        CarRentalDTO carRentalDTO = new CarRentalDTO();
        carRentalDTO.setCarDTO(carTransformer.fromEntity(carRental.getCar()));
        carRentalDTO.setUserDTO(displayUserTransformer.fromEntity(carRental.getUser()));
        carRentalDTO.setRentalDate(carRental.getRentalDate());
        return carRentalDTO;
    }
}
