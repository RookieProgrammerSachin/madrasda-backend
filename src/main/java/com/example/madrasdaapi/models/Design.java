package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "design", schema = "spring-madrasda", indexes = {
        @Index(name = "vendor_id", columnList = "vendor_id")
})
public class Design {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

     @Column(name = "theme", length = 100)
     private String theme;

     @Column(name = "img", nullable = false, length = 3000)
     private String img;

     @Column(name = "position", nullable = false, length = 55)
     private String position;

     @Column(name = "print_type", nullable = false, length = 55)
     private String printType;

     @OneToMany(mappedBy = "design")
     private Set<Product> products = new HashSet<>();

}