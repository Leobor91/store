package com.Store.Store.repository;

import com.Store.Store.domain.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

   Optional<Product> findById(String id);

}
