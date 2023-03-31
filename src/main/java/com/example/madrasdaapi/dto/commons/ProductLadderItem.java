package com.example.madrasdaapi.dto.commons;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductLadderItem implements Serializable {
     private Long id;
     private String name;
     private String imgUrl = "https://cdn.discordapp.com/attachments/1088691132210364437/1088693356714012692/image.png";
     private Long profitAmount;
     private Integer stocksSold;
     private Integer profit;
     private Integer returnsContribution;
}