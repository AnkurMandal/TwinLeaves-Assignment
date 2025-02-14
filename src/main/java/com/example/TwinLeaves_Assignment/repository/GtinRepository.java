package com.example.TwinLeaves_Assignment.repository;

import com.example.TwinLeaves_Assignment.model.Gtin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GtinRepository extends JpaRepository<Gtin, Integer> {
    @Query(value = "select distinct g.id, g.gtin, g.product_product_id from gtin_model g inner join batch b on b.gtin_model_id = g.id where b.available_quantity > 0", nativeQuery = true)
    List<Gtin> findGtinWithPositiveAvailableQuantity();
}
