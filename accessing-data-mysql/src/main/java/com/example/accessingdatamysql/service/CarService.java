package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CarDTO;

public interface CarService {
    CarDTO saveCar(CarDTO carDTO);
}
