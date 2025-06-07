package com.crud.crud_springboot.Repository;

import com.crud.crud_springboot.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByLabel(String label);
}
