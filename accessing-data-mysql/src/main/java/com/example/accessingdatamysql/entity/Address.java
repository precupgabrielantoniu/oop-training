package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="addresses")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor //needed for the builder
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
