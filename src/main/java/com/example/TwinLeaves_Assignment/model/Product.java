package com.example.TwinLeaves_Assignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Integer productId;

     String productName;

    @CreationTimestamp
     Date createdOn;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
     Gtin gtin;



    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "product_batch",
            joinColumns=@JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "batch_id"))
    List<Batch>batchList=new ArrayList<>();



}
