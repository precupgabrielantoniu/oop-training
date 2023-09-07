package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.CarDTO;
import com.example.accessingdatamysql.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarTransformer implements Transformer<Car, CarDTO>{
    public Car fromDTO(CarDTO carDTO){
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setStock(carDTO.getStock());
        return car;
    }

    public CarDTO fromEntity(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand(car.getBrand());
        carDTO.setStock(car.getStock());
        return carDTO;
    }
}
