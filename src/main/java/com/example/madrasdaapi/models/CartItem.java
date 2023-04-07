package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product;

     @ManyToOne
     @JoinColumn(name = "customer_id")
     private Customer customer;

     private Integer quantity;
}