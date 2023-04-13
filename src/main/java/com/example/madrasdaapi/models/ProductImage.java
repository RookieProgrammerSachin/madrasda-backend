package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product_images", schema = "spring-madrasda")
@Getter
@Setter
public class ProductImage {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(length = 1000)
     private String imgUrl;

     @ManyToOne
     private Color color;

     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     private Product product;
}
