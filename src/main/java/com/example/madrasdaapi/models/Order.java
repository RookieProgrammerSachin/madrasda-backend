package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders", schema = "spring-madrasda", indexes = {
        @Index(name = "transaction_id", columnList = "transaction_id")
})
public class Order {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "transaction_id", nullable = false)
     private Transaction transaction;

     @Column(name = "status", nullable = false, length = 55)
     private String status;

}