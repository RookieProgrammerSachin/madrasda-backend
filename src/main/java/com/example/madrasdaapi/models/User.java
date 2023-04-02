package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "madrasda")
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private Long id;

     @Size(max = 255)
     @Column(name = "email")
     private String email;

     @Size(max = 255)
     @Column(name = "name")
     private String name;

     @Size(max = 15)
     @Column(name = "phone", length = 15)
     private String phone;

     @Size(max = 1500)
     @Column(name = "password", length = 1500)
     private String password;

     @Size(max = 255)
     @Column(name = "role")
     private String role;

}