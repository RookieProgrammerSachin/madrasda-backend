package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer", schema = "spring-madrasda", uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = {"id"})
})
public class Customer {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(referencedColumnName = "id", nullable = false)
     private User user;

     private Boolean isBillingUser;

     private String name;

     @Column(name = "address_line1",  length = 50)
     private String addressLine1;

     @Column(name = "address_line2", length = 50)
     private String addressLine2;

     @Column(name = "city",  length = 40)
     private String city;

     @Column(name = "state",  length = 40)
     private String state;

     @Column(name = "postal_code",  length = 40)
     private String postalCode;

     @Column(name = "country",  length = 100)
     private String country;

     private String phone;

     private String email;


}