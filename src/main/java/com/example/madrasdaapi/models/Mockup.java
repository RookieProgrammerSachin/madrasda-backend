package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mockup", schema = "madrasda")
public class Mockup {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "img_path", nullable = false, length = 2000)
     private String imgPath;

     @Column(name = "name", nullable = false, length = 200)
     private String name;

     @Column(name = "color", nullable = false, length = 10)
     private String color;

     @Column(name = "size", nullable = false, length = 10)
     private String size;

     @Column(name = "quantity", nullable = false)
     private Integer quantity;

     @Column(name = "model", nullable = false, length = 10)
     private String model;

     @Column(name = "type", nullable = false, length = 200)
     private String type;

     @Column(name = "category", nullable = false, length = 200)
     private String category;

}