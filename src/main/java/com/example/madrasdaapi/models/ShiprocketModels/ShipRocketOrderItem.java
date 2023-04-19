package com.example.madrasdaapi.models.ShiprocketModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ShipRocketOrderItem implements Serializable {
    private String name;
    private String sku;
    private Integer units;
    @JsonProperty("selling_price")
    private String sellingPrice;
    private String discount;
    private String tax;
    private Integer hsn;

}