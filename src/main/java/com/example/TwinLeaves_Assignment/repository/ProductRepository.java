package com.example.TwinLeaves_Assignment.repository;

import com.example.TwinLeaves_Assignment.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

