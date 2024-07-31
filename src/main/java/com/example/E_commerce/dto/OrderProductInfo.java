package com.example.E_commerce.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter

public class OrderProductInfo {

    private Long productId;
    private int quantity;
}
