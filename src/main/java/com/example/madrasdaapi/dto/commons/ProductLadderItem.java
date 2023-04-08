package com.example.madrasdaapi.dto.commons;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class ProductLadderItem implements Serializable {
     private Long id;
     private String name;
     private String imgUrl = "https://cdn.discordapp.com/attachments/1088691132210364437/1088693356714012692/image.png";
     private Float profitAmount;
     private Long stocksSold;
     private Float profit;
     private Float returnsContribution;
}