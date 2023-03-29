package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "madrasda")
public class User {
     @Id
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "name")
     private String name;

     @Column(name = "email")
     private String email;

     @Column(name = "phone", length = 11)
     private String phone;

     @Column(name = "password", length = 32)
     private String password;

     @OneToOne
     @JoinTable(name = "user_role", joinColumns = @JoinColumn(referencedColumnName = "id", name = "user_id"),
             inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
     private Role role;
     @OneToOne(mappedBy = "user")
     private Vendor vendor;

     @OneToOne(mappedBy = "user")
     private Customer customer;

}