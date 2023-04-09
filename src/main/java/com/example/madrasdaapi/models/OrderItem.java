package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "transaction_product")
public class OrderItem {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private int quantity;

     private String sku;

     @OneToOne
     private Size size;

     @OneToOne
     private Color color;

     @ManyToOne
     private Product product;
     @ManyToOne
     private Transaction transaction;
}
