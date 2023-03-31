package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "customer", schema = "madrasda", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = {"id"})
})
public class Customer {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @MapsId
     @OneToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "id", nullable = false)
     private User user;

     @Column(name = "address_line1", nullable = false, length = 50)
     private String addressLine1;

     @Column(name = "address_line2", length = 50)
     private String addressLine2;

     @Column(name = "city", nullable = false, length = 40)
     private String city;

     @Column(name = "state", nullable = false, length = 40)
     private String state;

     @Column(name = "postal_code", nullable = false, length = 40)
     private String postalCode;

     @Column(name = "country", nullable = false, length = 100)
     private String country;

     @OneToMany(mappedBy = "customer")
     private Set<Transaction> transactions = new HashSet<>();

}