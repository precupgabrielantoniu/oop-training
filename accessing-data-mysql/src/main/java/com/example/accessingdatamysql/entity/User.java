package com.example.accessingdatamysql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity // This tells Hibernate to make a table out of this class
@Getter
@Setter
//@Table(name="user") works if all the constraints are specified before creating the table.
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    //@Column(unique = true) would work if the constraint would've been created at the same time as the table.
    private String name;

    // @Column(name="email") by default
    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name = "owner_id", updatable = false)
    private Set<Book> books;

    @ManyToMany
    @JoinTable(
            name = "cart",
            joinColumns = @JoinColumn(name = "buyer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    @OneToMany(mappedBy = "user")
    Set<CarRental> carRentals;
}