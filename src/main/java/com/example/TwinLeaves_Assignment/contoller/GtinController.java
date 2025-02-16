package com.example.TwinLeaves_Assignment.contoller;


import com.example.TwinLeaves_Assignment.model.Gtin;
import com.example.TwinLeaves_Assignment.service.GtinService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gtin")
public class GtinController {
    @Autowired
    private GtinService gtinService;

    @PostMapping("/addGtin")
    public ResponseEntity<String> addGtin(@RequestBody Gtin gtin, @RequestParam("product-id") Integer productId){
        try{
            String result = gtinService.addGtin(gtin,productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getGtin")
    public ResponseEntity<List<Gtin>> getGtin(){
        try{
            List<Gtin> result = gtinService.getGtin();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/positiveAvailableQuantity")
    public ResponseEntity<List<Gtin>> positiveAvailableQuantity(){
        try{
            List<Gtin> result = gtinService.positiveAvailableQuantity();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
