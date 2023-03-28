package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product", schema = "madrasda", indexes = {
        @Index(name = "design_id", columnList = "design_id"),
        @Index(name = "mockup_id", columnList = "mockup_id")
})
public class Product {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "mockup_id", nullable = false)
     private Mockup mockup;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "design_id", nullable = false)
     private Design design;

     @Column(name = "base_price", nullable = false)
     private Float basePrice;

     @Column(name = "shipping", nullable = false)
     private Float shipping;

     @Column(name = "discount")
     private Float discount;

     @Column(name = "total", nullable = false)
     private Float total;

     @Column(name = "profit")
     private Float profit;

     @Column(name = "tax", nullable = false)
     private Float tax;

     @Column(name = "publish_status", nullable = false)
     private Boolean publishStatus = false;

     @OneToMany(mappedBy = "product")
     private Set<Image> images = new HashSet<>();

     @ManyToMany
     @JoinTable(name = "transaction_product",
             joinColumns = @JoinColumn(name = "product_id"),
             inverseJoinColumns = @JoinColumn(name = "transaction_id"))
     private Set<Transaction> transactions = new HashSet<>();

}