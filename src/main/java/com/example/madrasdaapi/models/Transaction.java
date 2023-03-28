package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "madrasda", indexes = {
        @Index(name = "address_id", columnList = "address_id"),
        @Index(name = "client_id", columnList = "client_id")
})
public class Transaction {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "client_id", nullable = false)
     private Client client;

     @Column(name = "order_date", nullable = false)
     private LocalDate orderDate;

     @Column(name = "expected_date")
     private LocalDate expectedDate;

     @Column(name = "order_total", nullable = false)
     private Integer orderTotal;

     @Column(name = "delivered", nullable = false)
     private Boolean delivered = false;

     @Column(name = "transaction_success", nullable = false)
     private Boolean transactionSuccess = false;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "address_id")
     private Address address;

     @OneToMany(mappedBy = "transaction")
     private Set<Order> orders = new HashSet<>();

     @ManyToMany
     @JoinTable(name = "transaction_product",
             joinColumns = @JoinColumn(name = "transaction_id"),
             inverseJoinColumns = @JoinColumn(name = "product_id"))
     private Set<Product> products = new HashSet<>();

}