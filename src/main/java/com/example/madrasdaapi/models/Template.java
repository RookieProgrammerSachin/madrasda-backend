package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "templates", schema = "madrasda", indexes = {
        @Index(name = "fk_templates_mockup1_idx", columnList = "mockup_id"),
        @Index(name = "fk_templates_vendor1_idx", columnList = "vendor_id")
})
public class Template {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @MapsId("designId")
     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "design_id", nullable = false)
     private Design design;

     @MapsId("mockupId")
     @ManyToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "mockup_id", nullable = false)
     private Mockup mockup;

     @MapsId("vendorId")
     @ManyToOne(fetch = FetchType.LAZY, optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

}