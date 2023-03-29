package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "mockup", schema = "madrasda", indexes = {
        @Index(name = "fk_mockup_vendor1_idx", columnList = "vendor_id")
})
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

     @ManyToOne
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

     @OneToMany(mappedBy = "mockup")
     private Set<Product> products = new HashSet<>();

}