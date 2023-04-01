package com.example.madrasdaapi.dto.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAnalysis implements Serializable {
     private Long totalProducts;
     private Long totalOrders;
     private Long totalProfit;
//     private List<Long> monthlySales = List.of(200L, 33L, 400L ,55L,11L, 3L, 555L, 109L, 12L ,12L ,333L, 44L);
}
