package com.example.madrasdaapi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images", schema = "madrasda")
public class ProductImage {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String imgUrl;
     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     Product product;
}
