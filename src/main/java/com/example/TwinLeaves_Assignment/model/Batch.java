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
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer batchId;


    Integer mrp;
    Integer sp;
    Integer availableQuantity;
    Integer purchasePrice;

    @CreationTimestamp
    Date inwardedOn;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "gtin_id")
     Gtin gtin;

    @JsonIgnore
    @ManyToMany(mappedBy = "batchList", cascade = CascadeType.ALL)
     List<Product> productList = new ArrayList<>();
}
