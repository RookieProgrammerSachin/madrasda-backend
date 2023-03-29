package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles", schema = "madrasda")
public class Role {
     @Id
     @Column(name = "id", nullable = false)
     private Integer id;

     @Column(name = "role", length = 45)
     private String role;

     @ManyToMany
     @JoinTable(name = "user_roles",
             joinColumns = @JoinColumn(name = "role_id"),
             inverseJoinColumns = @JoinColumn(name = "user_id"))
     private Set<User> users = new HashSet<>();

}