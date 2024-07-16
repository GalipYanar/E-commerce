package com.example.E_commerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name="customer")
@Entity
@Getter
@Setter

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Adress adress;

    private String roles;
}
