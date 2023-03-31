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
        @Index(name = "address_id", columnList = "customer_id")
})
public class Transaction {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "order_date", nullable = false)
     private LocalDate orderDate;

     @Column(name = "expected_date")
     private LocalDate expectedDate;

     @Column(name = "order_total", nullable = false)
     private Integer orderTotal;

     @Column(name = "delivered", nullable = false)
     private Byte delivered;

     @Column(name = "transaction_success", nullable = false)
     private Byte transactionSuccess;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "customer_id", nullable = false)
     private Customer customer;

     @ManyToOne
     @JoinTable(name = "transaction_vendor",
             joinColumns = @JoinColumn(name = "transaction_id"),
             inverseJoinColumns = @JoinColumn(name = "vendor_id"))
     private Vendor vendors;

     @OneToMany
     private Set<Product> products;

     @OneToMany(mappedBy = "transaction")
     private Set<Order> orders = new HashSet<>();

}