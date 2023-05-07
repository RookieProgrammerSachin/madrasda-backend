package com.example.madrasdaapi.dto.ShiprocketModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ShipRocketOrderItem implements Serializable {
    private String name;
    private String sku;
    private Integer units;
    @JsonProperty("selling_price")
    private String sellingPrice;
    private BigDecimal discount;
    private BigDecimal tax;
    private Integer hsn;

}