package com.example.accessingdatamysql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Setter
public class CarRentalKey implements Serializable {
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "car_id")
    Integer carId;

    @Override
    public boolean equals(Object carRentKeyObject){
        CarRentalKey carRentalKey = (CarRentalKey) carRentKeyObject;
        return (Objects.equals(this.userId, carRentalKey.userId) && Objects.equals(this.carId, carRentalKey.carId));
    }

    @Override
    public int hashCode(){
        String string = "" + userId + "000" + carId;
        return Integer.parseInt(string);
    }
}
