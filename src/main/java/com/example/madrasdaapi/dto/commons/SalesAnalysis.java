package com.example.madrasdaapi.dto.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAnalysis implements Serializable {
     private Long totalProducts;
     private Long totalOrders;
     private Long totalProfit;
     private Integer productsSoldToday;
     private List<Double> monthlySales;
}
