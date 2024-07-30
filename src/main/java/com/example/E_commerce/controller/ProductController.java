package com.example.E_commerce.controller;

import com.example.E_commerce.model.Product;
import com.example.E_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(name = "/create" ,consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> createProduct(@RequestPart("file")MultipartFile file,
                                                 @ModelAttribute Product product){
        return new ResponseEntity<>(productService.createProduct(file, product), HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getProductList(@PathVariable(value = "categoryId") Long categoryId){
        return new ResponseEntity<>(productService.getProductListByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id){
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }
}
