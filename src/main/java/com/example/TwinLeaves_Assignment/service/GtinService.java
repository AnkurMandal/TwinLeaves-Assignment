package com.example.TwinLeaves_Assignment.service;

import com.example.TwinLeaves_Assignment.model.Gtin;
import com.example.TwinLeaves_Assignment.model.Product;
import com.example.TwinLeaves_Assignment.repository.GtinRepository;
import com.example.TwinLeaves_Assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GtinService {

    @Autowired
    private GtinRepository gtinRepository;

    @Autowired
    private ProductRepository productRepository;

    public String addGtin(Gtin gtin,Integer productId) throws Exception{
        if(gtin.getId() != null){
            throw new Exception("Gtin already exists.");
        }
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isEmpty()){
            throw new Exception("productId is invalid!");
        }
        Product product = optionalProduct.get();

        product.setGtin(gtin);

        gtin.setProduct(product);

        productRepository.save(product);

//        gtinRepository.save(gtin);

        return "Gtin has been successfully save into Database.";
    }

    public List<Gtin> getGtin() throws Exception{
        List<Gtin> gtinModelList = gtinRepository.findAll();

        if(gtinModelList.isEmpty()){
            throw new Exception("Gtin is Empty!");
        }
        return gtinModelList;
    }

    public List<Gtin> positiveAvailableQuantity() throws Exception{
        List<Gtin> filteredGtinModels = gtinRepository.findGtinWithPositiveAvailableQuantity();

        if(filteredGtinModels.isEmpty()){
            throw new Exception("Gtin is Empty with Positive Available Quantity!");
        }

        return filteredGtinModels;



    }
}