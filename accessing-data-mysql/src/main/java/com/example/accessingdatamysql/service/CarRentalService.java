package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CarRentalDTO;
import com.example.accessingdatamysql.errorhandling.NoCarStockException;
import com.example.accessingdatamysql.errorhandling.NoCarWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;

public interface CarRentalService {
    CarRentalDTO saveCarRental(Integer userId, Integer carId) throws NoCarStockException, NoCarWithIdException, NoUserWithIdException;
}
