package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String category;

    private String company;

    private Double price;

    @ManyToMany(mappedBy = "products")
    private Set<User> buyers;
}
