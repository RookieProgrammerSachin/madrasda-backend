package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product",schema = "spring-madrasda", indexes = {
        @Index(name = "fk_product_vendor1_idx", columnList = "vendor_id"),

})
@Cacheable
public class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     private String name;

     private String audience;

     @Column(length = 4000)
     private String description;

     @Column(columnDefinition = "decimal(10,3) default '0.000'")
     private BigDecimal basePrice;


     @Column(columnDefinition = "decimal(10,3) default '0.000'")
     private BigDecimal discount;

     @Column(columnDefinition = "decimal(10,3) default '0.000'")
     private BigDecimal total;

     @Column(columnDefinition = "decimal(10,3) default '0.000'")
     private BigDecimal profit;

     @Column(columnDefinition = "decimal(10,3) default '0.000'")
     private BigDecimal tax;

     private Integer hsn;

     private Boolean publishStatus;

     @Column(columnDefinition = "boolean default false")
     private boolean adminBan;

     @Column(length = 1000)
     private String frontDesignUrl;

     private String frontDesignPlacement;

     @Column(length = 1000)
     private String backDesignUrl;

     private String backDesignPlacement;

     private Float height;

     private Float breadth;

     private Float length;

     private Float weight;

     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
     List<ProductImage> productImages = new ArrayList<>();

     @ManyToOne(optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

     @ManyToMany
     @JoinTable(joinColumns = @JoinColumn(referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
     @BatchSize(size = 15)
     private List<ProductSKUMapping> skuMappings;

     @ManyToOne
     private Mockup mockup;
}
