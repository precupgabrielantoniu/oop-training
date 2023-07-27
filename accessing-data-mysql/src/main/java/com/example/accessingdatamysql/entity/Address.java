package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name="addresses")
@Builder
@Getter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer number;

    private String street;

    private String city;

    private String county;

    @OneToOne(mappedBy = "address")
    private User user;
}
