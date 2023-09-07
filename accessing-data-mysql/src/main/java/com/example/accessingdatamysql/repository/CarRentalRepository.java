package com.example.accessingdatamysql.repository;

import com.example.accessingdatamysql.entity.CarRental;
import com.example.accessingdatamysql.entity.CarRentalKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepository extends CrudRepository<CarRental, CarRentalKey> {
}
