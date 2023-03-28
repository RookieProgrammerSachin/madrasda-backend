package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendor", schema = "madrasda", indexes = {
        @Index(name = "mail_id", columnList = "mail_id", unique = true)
})
public class Vendor {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "name", nullable = false, length = 55)
     private String name;

     @Column(name = "phone", nullable = false, length = 55)
     private String phone;

     @Column(name = "mail_id", nullable = false, length = 55)
     private String mailId;

     @Column(name = "pswd", nullable = false, length = 2000)
     private String password;

     @Column(name = "profile_pic", nullable = false, length = 1000)
     private String profilePic;

     @OneToMany(mappedBy = "vendor")
     private Set<Design> designs = new HashSet<>();

     @OneToMany(mappedBy = "vendor")
     private Set<Feedback> feedbacks = new HashSet<>();

}