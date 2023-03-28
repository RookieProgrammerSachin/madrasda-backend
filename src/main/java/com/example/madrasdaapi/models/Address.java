package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "address", schema = "spring-madrasda", indexes = {
        @Index(name = "client_id", columnList = "client_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = {"id"})
})
public class Address {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "address_line1", nullable = false, length = 50)
     private String addressLine1;

     @Column(name = "address_line2", length = 50)
     private String addressLine2;

     @Column(name = "city", nullable = false, length = 40)
     private String city;

     @Column(name = "state", nullable = false, length = 40)
     private String state;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "client_id", nullable = false)
     private Client client;

     @Column(name = "postal_code", nullable = false, length = 40)
     private String postalCode;

     @Column(name = "country", nullable = false, length = 100)
     private String country;

     @Column(name = "fname", length = 60)
     private String fname;

     @Column(name = "lname", length = 60)
     private String lname;

     @OneToMany(mappedBy = "address")
     private Set<Transaction> transactions = new HashSet<>();

}