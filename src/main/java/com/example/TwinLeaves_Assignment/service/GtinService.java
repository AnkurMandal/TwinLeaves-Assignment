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

    public String issueProduct(Integer id, Integer productId) throws Exception{
        Optional<Gtin> optionalGtinModel = gtinRepository.findById(id);
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalGtinModel.isEmpty()){
            throw new Exception("gtin_id is invalid!");
        }
        if(optionalProduct.isEmpty()){
            throw new Exception("productId is invalid!");
        }

        Gtin gtin = optionalGtinModel.get();
        Product product = optionalProduct.get();

        product.setGtin(gtin);
        gtin.setProduct(product);

        productRepository.save(product);
        return "Product successfully issued to Gtin.";
    }

    public String addGtin(Gtin gtinModel) throws Exception{
        if(gtinModel.getId() != null){
            throw new Exception("Gtin already exists.");
        }
        gtinRepository.save(gtinModel);
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