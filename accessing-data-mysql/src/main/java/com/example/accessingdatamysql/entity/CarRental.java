package com.example.accessingdatamysql.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "car_rentals")
public class CarRental {
    @EmbeddedId
    CarRentalKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name = "car_id")
    Car car;

    Date rentalDate;
}
