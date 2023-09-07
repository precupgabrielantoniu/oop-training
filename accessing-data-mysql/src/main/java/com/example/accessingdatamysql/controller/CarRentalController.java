package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.dto.CarRentalDTO;
import com.example.accessingdatamysql.errorhandling.NoCarStockException;
import com.example.accessingdatamysql.errorhandling.NoCarWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.service.CarRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/demo/car_rental")
@CrossOrigin(origins = "*",  allowedHeaders="*")
public class CarRentalController {
    @Autowired
    CarRentalService carRentalService;

    @PostMapping(path="/rent_car/{user_id}/{car_id}")
    public @ResponseBody CarRentalDTO addProductToUser(@PathVariable(value = "user_id") Integer userId, @PathVariable(value = "car_id") Integer carId){
        try {
            return carRentalService.saveCarRental(userId, carId);
        } catch(NoUserWithIdException | NoCarWithIdException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch(NoCarStockException e){
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, e.getMessage(), e);
        }
    }
}
