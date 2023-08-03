package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer pageCount;

    private String publisher;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private User owner;
}
