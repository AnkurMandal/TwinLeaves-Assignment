package com.example.TwinLeaves_Assignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Gtin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    String gtin;

    @JsonManagedReference
    @OneToMany(mappedBy = "gtin", cascade = CascadeType.ALL)
     List<Batch> batchList = new ArrayList<>();


    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    Product product;

}
