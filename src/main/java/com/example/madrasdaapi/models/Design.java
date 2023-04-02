package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Designs", schema = "madrasda")
public class Design {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String imgUrl;

     @Column(nullable = false)
     private String designType;

     private String theme;

     private String additionalInformation;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "vendorID", nullable = false)
     private Vendor vendor;


}