package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mockups", schema = "madrasda")
public class Mockup {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     private String frontImage;

     private String backImage;

     private String productType;

     private String category;

     private String model;

     @OneToMany
     private List<Color> colors;

     @OneToMany
     private List<Size> sizes;

     private String additionalInformation;
}
