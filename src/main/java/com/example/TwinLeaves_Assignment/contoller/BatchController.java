package com.example.TwinLeaves_Assignment.contoller;

import com.example.TwinLeaves_Assignment.model.Batch;
import com.example.TwinLeaves_Assignment.model.Product;
import com.example.TwinLeaves_Assignment.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @PostMapping("/addBatch")
    public ResponseEntity<String> addBatch(@RequestBody Batch batch,@RequestParam("gtin-id") Integer gtinId){
        try{
            String result = batchService.addBatch(batch,gtinId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    @GetMapping("inwardedOn")
    public ResponseEntity<Batch> inwardedOn(){
        try{
            Batch result = batchService.inwardedOn();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get-all-Batch")
    public ResponseEntity<List<Batch>> allBatch(){
        try{
            List<Batch> result = batchService.allBatch();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
