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
@Table(name = "product", schema = "madrasda", indexes = {
        @Index(name = "fk_product_vendor1_idx", columnList = "vendor_id"),

})
public class Product {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;

     private String name;

     private String audience;

     private Float basePrice;

     private Float shipping;

     private Float discount;

     private Float total;

     private Float profit;

     private Float tax;

     private Boolean publishStatus;

     @ManyToOne(optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

     @OneToOne
     @JoinColumn(referencedColumnName = "id")
     private Mockup mockup;


     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     List<ProductImage> productImages;

     private String frontDesignUrl;

     @JdbcTypeCode(SqlTypes.JSON)
     private String frontDesignPlacement;

     private String backDesignUrl;

     @JdbcTypeCode(SqlTypes.JSON)
     private String backDesignPlacement;

     @OneToMany
     private List<ProductSKUMapping> skuMappings;
}
