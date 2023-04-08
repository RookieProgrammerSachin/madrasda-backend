package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
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

     private Date orderDate;

     private Integer orderTotal;

     private String paymentStatus;

     private Integer paymentId;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "customer_id", nullable = false)
     private Customer customer;

     @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
     private Shipment shipment;

     @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
     private Set<OrderItem> orderItems = new HashSet<>();

}