package com.example.TwinLeaves_Assignment.service;


import com.example.TwinLeaves_Assignment.model.Batch;
import com.example.TwinLeaves_Assignment.model.Gtin;
import com.example.TwinLeaves_Assignment.model.Product;
import com.example.TwinLeaves_Assignment.repository.BatchRepository;
import com.example.TwinLeaves_Assignment.repository.GtinRepository;
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

    public String addBatch(Batch batch) throws Exception{
        if(batch.getBatchId() != null){
            throw new Exception("Batch already exists.");
        }
        batchRepository.save(batch);
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

    public String issueProduct(Integer id, Integer batchId) throws Exception{
        Optional<Gtin> optionalGtinModel = gtinRepository.findById(id);
        Optional<Batch> optionalBatch = batchRepository.findById(batchId);

        if(optionalGtinModel.isEmpty()){
            throw new Exception("gtin_id is invalid!");
        }
        if(optionalBatch.isEmpty()){
            throw new Exception("batchId is invalid!");
        }

        Gtin gtin = optionalGtinModel.get();
        Batch batch = optionalBatch.get();

        batch.setGtin(gtin);
        gtin.getBatchList().add(batch);

        gtinRepository.save(gtin);
        return "batch successfully issued to Gtin.";
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
