package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CarDTO;
import com.example.accessingdatamysql.entity.Car;
import com.example.accessingdatamysql.repository.CarRepository;
import com.example.accessingdatamysql.transformers.CarTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarTransformer carTransformer;

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = carRepository.save(carTransformer.fromDTO(carDTO));
        return carTransformer.fromEntity(car);
    }
}
