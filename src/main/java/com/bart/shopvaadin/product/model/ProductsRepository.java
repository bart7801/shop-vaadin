package com.bart.shopvaadin.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {

    Optional<Product> getByName(String name);

    Product getById(Long id);

    @Query("select p from Product p where p.quantity > 0")
    List<Product> getAvailableProducts();

    @Transactional
    @Modifying
    @Query("update Product p set p.quantity = p.quantity - 1 where p.id = :id")
    void decreaseQuantity(@Param("id") Long id);

}
