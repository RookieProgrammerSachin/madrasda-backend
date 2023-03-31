package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

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

     @Column(name = "base_price", nullable = false)
     private Float basePrice;

     @Column(name = "shipping", nullable = false)
     private Float shipping;

     @Column(name = "discount")
     private Float discount;

     @Column(name = "total", nullable = false)
     private Float total;

     @Column(name = "profit")
     private Float profit;

     @Column(name = "tax", nullable = false)
     private Float tax;

     @Column(name = "publish_status", nullable = false)
     private Boolean publishStatus;

     @ManyToOne(optional = false)
     @JoinColumn(name = "vendor_id", nullable = false)
     private Vendor vendor;

     @ManyToMany(mappedBy = "products")
     private Set<Transaction> transaction;

     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     @JoinTable(name="product_image_mapping", joinColumns = @JoinColumn(referencedColumnName = "id"),
     inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
     Set<ProductImage> productImages;
}
