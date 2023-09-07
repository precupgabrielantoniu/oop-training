package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CarDTO;
import com.example.accessingdatamysql.entity.Car;
import com.example.accessingdatamysql.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = carRepository.save(CarDTO.fromDTO(carDTO));
        return CarDTO.fromEntity(car);
    }
}
