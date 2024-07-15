package com.example.E_commerce.repository;

import com.example.E_commerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository <Customer, Long>{

    @Query("Select c from Customer c Where c.email = email")
    Optional<Customer> findByEmail(String email);
}
