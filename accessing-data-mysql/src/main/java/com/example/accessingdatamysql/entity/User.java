package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
@Table(name="user")
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

    @OneToMany
    @JoinColumn(name = "owner_id")
    private Set<Book> books;

}