package com.siemens.productapi.repository;

import com.siemens.productapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
