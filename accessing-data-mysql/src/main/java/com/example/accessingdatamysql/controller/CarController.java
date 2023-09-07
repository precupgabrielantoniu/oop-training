package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.dto.CarDTO;
import com.example.accessingdatamysql.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/demo/car")
@CrossOrigin(origins = "*",  allowedHeaders="*")
public class CarController {
    @Autowired
    CarService carService;

    @PostMapping(path="/add")
    public @ResponseBody CarDTO addCar(@RequestBody CarDTO carDTO){
        return carService.saveCar(carDTO);
    }
}
