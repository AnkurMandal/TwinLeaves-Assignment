package com.example.TwinLeaves_Assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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


    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Batch batch;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Gtin gtin;

}
