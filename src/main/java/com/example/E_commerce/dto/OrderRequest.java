package com.example.E_commerce.dto;

import com.example.E_commerce.model.Order;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter

public class OrderRequest {

    private Long customerId;

    private List<OrderProductInfo> orderList;
}
