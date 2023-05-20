package com.example.madrasdaapi.models;

import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NamedStoredProcedureQuery(
        name = "GET_SALES_ANALYSIS_BY_VENDOR_ID",
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
        }
)
@NamedStoredProcedureQuery(
        name = "products_sold_today",
        procedureName = "products_sold_today",
        parameters = {
                @StoredProcedureParameter(name = "vendor_id", mode = ParameterMode.IN, type = Long.class),
        },
        resultSetMappings = {"productsSoldMapping"})
@SqlResultSetMapping(
        name = "productsSoldMapping",
        classes = {
                @ConstructorResult(
                        targetClass = Integer.class,
                        columns = {
                                @ColumnResult(name = "sold_today", type = Integer.class)
                        })
        }
)

@Table(name = "vendor", schema = "madrasda")
public class Vendor {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
     private Long id;

     @MapsId
     @OneToOne(fetch = FetchType.EAGER, optional = false, cascade = CascadeType.ALL)
     @JoinColumn(name = "id", nullable = false)
     private User user;

     @Size(max = 1000)
     @Column(name = "profile_pic", length = 1000)
     private String profilePic;

     @Size(max = 500)
     private String companyName;

     @Size(max = 500)
     private String companyUrl;

     @Size(max = 16)
     private String GSTIN;

     @Size(max = 255)
     @Column(name = "category")
     private String category;

     @Column(columnDefinition = "decimal(10,3) default '0.000'")
     private BigDecimal outstandingProfit = new BigDecimal(0L);

     @Column(columnDefinition = "bit(1) default b'1'")
     private Boolean status = true;

     @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
     private List<Design> designs = new ArrayList<>();

     private Boolean payoutRequested = false;

}