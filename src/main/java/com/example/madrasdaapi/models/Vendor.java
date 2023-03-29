package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendor", schema = "madrasda")
public class Vendor {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @MapsId
     @OneToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "id", nullable = false)
     private User user;

     @Column(name = "profile_pic", nullable = false, length = 1000)
     private String profilePic;

     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Design> designs = new HashSet<>();

     @ManyToMany
     @JoinTable(name = "transaction_vendor",
             joinColumns = @JoinColumn(name = "vendor_id"),
             inverseJoinColumns = @JoinColumn(name = "transaction_id"))
     private Set<Transaction> transactions = new HashSet<>();

     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Product> products = new HashSet<>();

     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Mockup> mockups = new HashSet<>();

     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Feedback> feedbacks = new HashSet<>();

}