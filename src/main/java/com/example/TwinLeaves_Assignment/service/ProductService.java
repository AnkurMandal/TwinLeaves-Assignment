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

    public String issueProduct(Integer batchId, Integer productId) throws Exception{
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalBatch.isEmpty()){
            throw new Exception("gtin_id is invalid!");
        }
        if(optionalProduct.isEmpty()){
            throw new Exception("productId is invalid!");
        }

        Batch batch = optionalBatch.get();
        Product product = optionalProduct.get();

        product.getBatchList().add(batch);
        batch.getProductList().add(product);

        batchRepository.save(batch);
        productRepository.save(product);
        return "Product successfully issued to Batch.";
    }
}
