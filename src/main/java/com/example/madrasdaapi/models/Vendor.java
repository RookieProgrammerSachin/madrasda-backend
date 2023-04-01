package com.example.madrasdaapi.models;

import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NamedStoredProcedureQuery(
        name = "getSalesAnalysisByVendorId",
        procedureName = "VENDOR_SALES",
        parameters = {
                @StoredProcedureParameter(name = "vendorId", mode = ParameterMode.IN, type = Long.class),
        },
        resultSetMappings = {"salesAnalysisMapping"})
@SqlResultSetMapping(
        name = "salesAnalysisMapping",
        classes = {
                @ConstructorResult(
                        targetClass = SalesAnalysis.class,
                        columns = {
                                @ColumnResult(name = "totalProducts", type = Long.class),
                                @ColumnResult(name = "totalOrders", type = Long.class),
                                @ColumnResult(name = "totalProfit", type = Long.class)
                        })
        })

@Table(name = "vendor", schema = "madrasda")
public class Vendor {
     String category;
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id", nullable = false)
     private Long id;
     @MapsId
     @OneToOne(fetch = FetchType.EAGER, optional = false)
     @JoinColumn(name = "id", nullable = false)
     private User user;
     @Column(name = "profile_pic", nullable = false, length = 1000)
     private String profilePic;
     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Design> designs = new HashSet<>();



     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Product> products = new HashSet<>();


     @OneToMany(mappedBy = "vendor", fetch = FetchType.LAZY)
     private Set<Feedback> feedbacks = new HashSet<>();

     @OneToMany(mappedBy = "vendor")
     private Set<Template> templates = new HashSet<>();

}