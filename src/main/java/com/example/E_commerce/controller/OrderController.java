package com.example.E_commerce.controller;

import com.example.E_commerce.dto.OrderRequest;
import com.example.E_commerce.model.Order;
import com.example.E_commerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping
    public ResponseEntity<Boolean> doOrder(@RequestBody OrderRequest orderRequest){
        return new ResponseEntity<>(orderService.doOrder(orderRequest), HttpStatus.OK);
    }
}
