package com.example.E_commerce.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter

public class Adress {

    private String country;
    private String district;
    private String city;
    private String postCode;
    private String adressLine;
}
