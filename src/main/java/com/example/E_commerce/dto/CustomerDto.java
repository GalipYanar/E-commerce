package com.example.E_commerce.dto;

import com.example.E_commerce.model.Adress;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Adress adress;
    private String roles;
}
