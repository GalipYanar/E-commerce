package com.example.E_commerce.controller;

import com.example.E_commerce.dto.AuthDto;
import com.example.E_commerce.dto.LoginDto;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.repository.CustomerRepository;
import com.example.E_commerce.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Customer> createCustomer (@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login (@RequestBody AuthDto authDto){
        return new ResponseEntity<>(customerService.login(authDto), HttpStatus.OK);
    }
}
