package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
public class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     private String name;

     private String audience;

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

     @ManyToOne(optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;


     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
     List<ProductImage> productImages = new ArrayList<>();

     @Column(length = 1000)
     private String frontDesignUrl;

     @JdbcTypeCode(SqlTypes.JSON)
     private String frontDesignPlacement;

     @Column(length = 1000)
     private String backDesignUrl;

     @JdbcTypeCode(SqlTypes.JSON)
     private String backDesignPlacement;

     @ManyToMany
     @JoinTable(joinColumns = @JoinColumn(referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
     private List<ProductSKUMapping> skuMappings;


     @ManyToOne
     private Mockup mockup;
}
