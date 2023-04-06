package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mockups", schema = "madrasda")
public class Mockup {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(nullable = false)
     private String frontImage;

     @Column(nullable = false)
     private String backImage;

     @Column(nullable = false)
     private String productType;

     private String category;
     private String model;

     @Column(name = "color")
     @JdbcTypeCode(SqlTypes.JSON)
     private List<String> color;

     @Column(name = "size")
     @JdbcTypeCode(SqlTypes.JSON)
     private List<String> size;

     private String additionalInformation;
}
