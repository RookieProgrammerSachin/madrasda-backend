package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "spring-madrasda")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private Long id;

     @Size(max = 500)
     @Column(name = "email", unique = true)
     private String email;

     @Size(max = 255)
     @Column(name = "name")
     private String name;

     @Size(max = 15)
     @Column(length = 15)
     private String phone;

     @Size(max = 1500)
     private String password;


     @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
     private List<CartItem> cart = new ArrayList<>();

     @OneToMany(mappedBy = "billingUser")
     private Set<Transaction> transactions = new HashSet<>();

     @Size(max = 255)
     @Column(name = "role")
     private String role;

}