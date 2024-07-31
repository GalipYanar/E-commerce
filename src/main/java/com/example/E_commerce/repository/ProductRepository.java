package com.example.E_commerce.repository;

import com.example.E_commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.categoryId = categoryId")
    List<Product> getProductListByCategoryId(@Param("categoryId") Long categoryId);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.active = :active WHERE p.id = :id")
    void updateProductActive(@Param("active") Boolean isActive, @Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.active = true")
    List<Product> getAllActiveProductList();

    @Query("SELECT p FROM Product p WHERE p.active = true AND p.id = :id")
    Optional<Product> getActiveProductById(@Param("id") Long id);
}
