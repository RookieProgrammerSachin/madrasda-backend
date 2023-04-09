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

     @ManyToOne
     @JoinColumn(name = "product_id")
     private Product product;

     @OneToOne
     private Size size;

     @OneToOne
     private Color color;

     @ManyToOne
     @JoinColumn(referencedColumnName = "id")
     private User customer;

     private Integer quantity;
}