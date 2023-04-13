package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = "spring-madrasda")
public class CartItem {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;


     @OneToOne
     private ProductSKUMapping sku;

     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     private User customer;

     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     private Product product;

     private Integer quantity;
}
