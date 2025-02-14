package com.example.TwinLeaves_Assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToMany(mappedBy = "gtin", cascade = CascadeType.ALL)
    private List<Batch> batchList = new ArrayList<>();

    @JsonIgnore
    @OneToOne
    @JoinColumn
    private Product product;

}
