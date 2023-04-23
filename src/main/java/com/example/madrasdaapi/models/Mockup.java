package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mockups", schema = "spring-madrasda")
public class Mockup {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     private String frontImage;

     private String backImage;

     private String productType;

     private String category;

     private String model;

     private Float height;

     private Float breadth;

     private Float length;

     private Float weight;

     private Integer hsn;

     private BigDecimal tax;

     private BigDecimal basePrice;

     private String additionalInformation;



     @OneToMany(mappedBy = "mockup", cascade = CascadeType.ALL)
     private List<ProductSKUMapping> skuMapping = new ArrayList<>();
}
