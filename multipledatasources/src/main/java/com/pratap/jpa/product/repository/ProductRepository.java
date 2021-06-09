package com.pratap.jpa.product.repository;

import com.pratap.jpa.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
