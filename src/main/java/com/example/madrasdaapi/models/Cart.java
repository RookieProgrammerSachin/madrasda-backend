package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @OneToOne
     private Customer customer;

     @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<CartItem> cartItems;
}
