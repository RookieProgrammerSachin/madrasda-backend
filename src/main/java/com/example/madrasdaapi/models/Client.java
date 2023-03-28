package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "client", schema = "madrasda", indexes = {
        @Index(name = "email_address", columnList = "email_address", unique = true),
        @Index(name = "phone_number", columnList = "phone_number", unique = true)
})
public class Client {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "phone_number", nullable = false, length = 40)
     private String phoneNumber;

     @Column(name = "email_address", length = 50)
     private String emailAddress;

     @OneToMany(mappedBy = "client")
     private Set<Address> addresses = new HashSet<>();

     @OneToMany(mappedBy = "client")
     private Set<Transaction> transactions = new HashSet<>();

}