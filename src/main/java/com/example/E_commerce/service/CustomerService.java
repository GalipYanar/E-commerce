package com.example.E_commerce.service;

import com.example.E_commerce.dto.AuthDto;
import com.example.E_commerce.dto.CustomerDto;
import com.example.E_commerce.dto.LoginDto;
import com.example.E_commerce.enums.RoleEnum;
import com.example.E_commerce.model.Customer;
import com.example.E_commerce.repository.CustomerRepository;

import com.example.E_commerce.util.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;


    public CustomerDto createCustomer(Customer customer){
        if (Objects.isNull(customer.getRoles())){
            customer.setRoles(RoleEnum.ROLE_USER.toString());
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return CustomerMapper.INSTANCE.customerToCustomerDto(customerRepository.save(customer));

    }

    public LoginDto login(AuthDto authDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(authentication);
        }
        throw new UsernameNotFoundException("İnvalid user details");
    }
}
