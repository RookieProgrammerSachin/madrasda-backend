package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "spring-madrasda", indexes = {
        @Index(name = "address_id", columnList = "customer_id")
})
public class Transaction {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;//

     @CreationTimestamp
     private Date orderDate;//

     private BigDecimal orderTotal;//

     private String paymentStatus;//

     private String paymentId;//

     private String orderId;//

     private Boolean billingIsShipping;//

     @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
     @JoinColumn(name = "customer_id")
     private Customer shippingAddress;//

     @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
     @JoinColumn(referencedColumnName = "id")
     private User billingUser;//

     @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
     private Shipment shipment;//

     @OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction")
     private List<OrderItem> orderItems = new ArrayList<>();//


}