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
@Table(name = "Templates")
public class Template {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @ManyToOne
     private Mockup mockup;

     @ManyToOne(cascade = CascadeType.ALL)
     private Design frontDesign;

     private String frontDesignPlacement;

     @ManyToOne(cascade = CascadeType.ALL)
     private Design backDesign;

     private String backDesignPlacement;

     @Column(name = "color")
     @JdbcTypeCode(SqlTypes.JSON)
     private List<String> colorPalette;

     @Column(name = "size")
     @JdbcTypeCode(SqlTypes.JSON)
     private List<String> sizes;

     @ManyToOne
     private Vendor vendor;

     @Column(name = "AdditionalInstructions")
     private String additionalInstructions;

     // Constructors, getters and setters
}
