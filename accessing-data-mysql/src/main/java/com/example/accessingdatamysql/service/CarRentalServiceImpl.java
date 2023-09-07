package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CarRentalDTO;
import com.example.accessingdatamysql.entity.Car;
import com.example.accessingdatamysql.entity.CarRental;
import com.example.accessingdatamysql.entity.CarRentalKey;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoCarWithIdException;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.CarRentalRepository;
import com.example.accessingdatamysql.repository.CarRepository;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    @Autowired
    CarRentalRepository carRentalRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public CarRentalDTO saveCarRental(Integer userId, Integer carId) throws Exception {
        Optional<Car> optionalCar = carRepository.findById(carId);
        Optional<User> optionalUser = userRepository.findById(userId);
        Car foundCar = optionalCar.orElseThrow(() -> new NoCarWithIdException("No car found with this id."));
        User foundUser = optionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        if (foundCar.getStock() == 0){
            throw new Exception("Not enough cars in stock.");
        }
        foundCar.setStock((foundCar.getStock()) - 1);
        CarRental carRental = new CarRental();
        carRental.setUser(foundUser);
        carRental.setCar(foundCar);
        CarRentalKey carRentalKey = new CarRentalKey();
        carRentalKey.setUserId(foundUser.getId());
        carRentalKey.setCarId(foundCar.getId());
        carRental.setId(carRentalKey);
        carRental.setRentalDate(Date.from(Instant.now()));
        CarRental carRentalFinal = carRentalRepository.save(carRental);
        foundUser.getCarRentals().add(carRentalFinal);
        foundCar.getCarRentals().add(carRentalFinal);
        userRepository.save(foundUser);
        carRepository.save(foundCar);

        return CarRentalDTO.fromEntity(carRentalFinal);
    }
}
