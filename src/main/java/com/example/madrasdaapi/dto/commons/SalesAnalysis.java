package com.example.madrasdaapi.dto.commons;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SalesAnalysis implements Serializable {
     private Long totalProducts;
     private Long totalOrders;
     private Long totalProfit;
     private Long totalPrintwearProfit;
     private List<Long> monthlySales = List.of(200L, 33L, 400L ,55L,11L, 3L, 555L, 109L, 12L ,12L ,333L, 44L);
}
