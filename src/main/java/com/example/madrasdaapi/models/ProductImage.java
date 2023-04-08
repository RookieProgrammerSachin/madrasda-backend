package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_images", schema = "madrasda")
@Getter
@Setter
public class ProductImage {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String imgUrl;

     @ManyToOne
     private Color color;

     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     private Product product;
}
