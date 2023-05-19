package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Entity
@Getter
@Setter
public class ProductSKUMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String sku;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Mockup mockup;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Size size;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @BatchSize(size = 30)
    private Color color;

    @Column(columnDefinition = "bit default true")
    private Boolean status;

}
