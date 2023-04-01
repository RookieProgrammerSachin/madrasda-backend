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
     Long id;
     String imgUrl;
     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     Product product;
}
