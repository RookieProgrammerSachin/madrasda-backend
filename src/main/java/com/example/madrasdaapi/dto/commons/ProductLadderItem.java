package com.example.madrasdaapi.dto.commons;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ProductLadderItem implements Serializable {
     private Long id;
     private String name;
     private String imgUrl;
     private BigDecimal profitAmount;
     private Long stocksSold;
     private Float profit;
     private Double returnsContribution;
}