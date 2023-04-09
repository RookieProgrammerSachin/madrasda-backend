package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Templates")
public class Template {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     private Mockup mockup;

     @ManyToOne
     private Design frontDesign;

     private String frontDesignPlacement;

     @ManyToOne
     private Design backDesign;

     private String backDesignPlacement;

     @ManyToOne
     private Vendor vendor;

     private String additionalInstructions;

}
