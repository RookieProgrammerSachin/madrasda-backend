package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedback", schema = "spring-madrasda", indexes = {
        @Index(name = "vendor_id", columnList = "vendor_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "id", columnNames = {"id"})
})
public class Feedback {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     @Column(name = "feedback", nullable = false, length = 2000)
     private String feedback;

     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

}