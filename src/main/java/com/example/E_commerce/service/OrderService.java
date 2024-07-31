package com.example.E_commerce.service;

import com.example.E_commerce.dto.OrderProductInfo;
import com.example.E_commerce.dto.OrderRequest;
import com.example.E_commerce.exception.ProductNotFoundException;
import com.example.E_commerce.model.Order;
import com.example.E_commerce.model.Product;
import com.example.E_commerce.repository.OrderRepository;
import com.example.E_commerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j

public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    private void productUnitStockCheck(List<OrderProductInfo> orderProductInfoList){
        orderProductInfoList.forEach(productInfo -> {
            Product product = productRepository.findById(productInfo.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found for this id: " + productInfo.getProductId()));

            if (product.getUnitInStock() - productInfo.getQuantity() < 0){
                log.error("The product stock insufficient: " + productInfo.getProductId());
                throw new RuntimeException("The product stock insufficient, productName= " + product.getName());
            }
        });
    }

    public boolean doOrder(OrderRequest orderRequest){
        log.info("Order request time {} customer: {}", LocalDateTime.now(), orderRequest.getCustomerId());
        productUnitStockCheck(orderRequest.getOrderList());

        orderRequest.getOrderList().forEach(orderRequestInfo -> {
            Order order = new Order();
            order.setCustomerId(orderRequest.getCustomerId());
            Product product = productRepository.getActiveProductById(orderRequestInfo.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found for this id: " + orderRequestInfo.getProductId()));
            Double totalPrice = orderRequestInfo.getQuantity() * product.getPrice();
            order.setTotalPrice(totalPrice);
            order.setQuantitiy(orderRequestInfo.getQuantity());
            order.setProductId(orderRequestInfo.getProductId());
            order.setCustomerId(orderRequest.getCustomerId());
            order.setPurchaseDate(LocalDate.now());

            // sipariş verildikten sonra, alınan ürün stoğu 0'a düşerse ürün false geçer

            if (product.getUnitInStock() - orderRequestInfo.getQuantity() == 0){
                product.setActive(false);
            }

            // order'ı kaydettikten sonra, stoktan order'daki ürün adedini düşer ve kaydeder
            orderRepository.save(order);

            product.setUnitInStock(product.getUnitInStock() - orderRequestInfo.getQuantity());
            productRepository.save(product);
        });
        return true;
    }
}
