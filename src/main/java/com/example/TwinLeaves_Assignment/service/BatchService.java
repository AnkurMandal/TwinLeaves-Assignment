package com.example.TwinLeaves_Assignment.service;


import com.example.TwinLeaves_Assignment.model.Batch;
import com.example.TwinLeaves_Assignment.model.Gtin;
import com.example.TwinLeaves_Assignment.model.Product;
import com.example.TwinLeaves_Assignment.repository.BatchRepository;
import com.example.TwinLeaves_Assignment.repository.GtinRepository;
import com.example.TwinLeaves_Assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BatchService {

    @Autowired
    private BatchRepository batchRepository;

    @Autowired
    private GtinRepository gtinRepository;



    public String addBatch(Batch batch,Integer gtinId) throws Exception{
        if(batch.getBatchId() != null){
            throw new Exception("Batch already exists.");
        }
        Optional<Gtin> optionalGtin = gtinRepository.findById(gtinId);

        if(optionalGtin.isEmpty()){
            throw new Exception("GtinId is invalid!");
        }
        Gtin gtin = optionalGtin.get();

        gtin.getBatchList().add(batch);


        batch.setGtin(gtin);



        gtinRepository.save(gtin);
//        batchRepository.save(batch);
        return "Batch has been successfully save into Database.";
    }

    public List<Product> allProductsWithBatch(Integer batchId) throws Exception{
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if(optionalBatch.isEmpty()){
            throw new Exception("BatchId is invalid!");
        }

        Batch batch = optionalBatch.get();
        List<Product> productList = batch.getProductList();

        return productList;
    }


    public Batch inwardedOn() throws Exception{
        List<Batch> batches = batchRepository.findBatchesWithNegativeOrZeroQuantityOrderByInwardedOn();
        if(batches.isEmpty()){
            throw new Exception("Latest batch not found!");
        }
        return batches.get(0);
    }

    public List<Batch> allBatch() throws Exception{
        List<Batch> batchList = batchRepository.findAll();

        if(batchList.isEmpty()){
            throw new Exception("Batch is empty!");
        }
        return batchList;
    }
}
