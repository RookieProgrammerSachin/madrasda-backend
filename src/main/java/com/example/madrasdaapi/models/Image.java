package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "image", schema = "spring-madrasda", indexes = {
        @Index(name = "product_id", columnList = "product_id")
})
public class Image {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "img_path", nullable = false, length = 2000)
     private String imgPath;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "product_id")
     private Product product;

}