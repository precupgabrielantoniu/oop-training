package com.example.accessingdatamysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;

    String brand;

    Integer stock;

    @OneToMany(mappedBy = "car")
    Set<CarRental> carRentals;
}
