package com.example.TwinLeaves_Assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Gtin gtin;

    @JsonIgnore
    @OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();
}
