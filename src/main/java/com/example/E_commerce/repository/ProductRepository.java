package com.example.E_commerce.repository;

import com.example.E_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.categoryId = categoryId")
    List<Product> getProductListByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT FROM Product p SET p.active = :active WHERE p.id = :id")
    Boolean updateProductActive(@Param("active") Boolean isActive, @Param("id") Long id);
}
