package com.example.madrasdaapi.models;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "madrasda", indexes = {
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

     @Column(columnDefinition = "bit(1) default b'0'")
     private Boolean billingIsShipping;//

     @Column(columnDefinition = "bit(1) default b'0'")
     private Boolean cancelled = false;

     @Column(columnDefinition = "bit(1) default b'0'")
     private Boolean cancelRequested = false;

     @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
     @JoinColumn(name = "customer_id")
     private Customer shippingAddress;//

     @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
     @JoinColumn(referencedColumnName = "id")
     private User billingUser;//

     @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
     private Shipment shipment;//

     @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "transaction")
     private List<OrderItem> orderItems = new ArrayList<>();//


}