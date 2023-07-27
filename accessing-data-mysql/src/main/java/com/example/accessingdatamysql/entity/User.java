package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String name;

    // @Column(name="email") by default
    private String email;

    private String password;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;


}