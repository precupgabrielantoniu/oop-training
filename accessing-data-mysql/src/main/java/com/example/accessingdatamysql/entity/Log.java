package com.example.accessingdatamysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Table(name="logs")
public class Log {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    private String message;

    private Date date;
}
