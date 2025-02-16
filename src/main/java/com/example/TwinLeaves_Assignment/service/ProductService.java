package com.example.TwinLeaves_Assignment.service;

import com.example.TwinLeaves_Assignment.model.Batch;
import com.example.TwinLeaves_Assignment.model.Product;
import com.example.TwinLeaves_Assignment.repository.BatchRepository;
import com.example.TwinLeaves_Assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BatchRepository batchRepository;

    public String addProduct(Product product) throws Exception{
        if(product.getProductId() != null){
            throw new Exception("Product already exists.");
        }
        productRepository.save(product);
        return "Product has been successfully save into Database.";
    }
}
