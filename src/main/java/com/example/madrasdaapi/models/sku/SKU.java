package com.example.madrasdaapi.models.sku;

import lombok.Getter;

@Getter
public enum SKU {
    PWRNWHXS_001M("WHITE-WH", "MENS RN", "XS", "PWRNWHXS_001M"), //White
    PWRNWHS_001M("WHITE-WH", "MENS RN", "S", "PWRNWHS_001M"),
    PWRNWHM_001M("WHITE-WH", "MENS RN", "M", "PWRNWHM_001M"),
    PWRNWHL_001M("WHITE-WH", "MENS RN", "L", "PWRNWHL_001M"),
    PWRNWHXL_001M("WHITE-WH", "MENS RN", "XL", "PWRNWHXL_001M"),
    PWRNWH2XL_001M("WHITE-WH", "MENS RN", "2XL", "PWRNWH2XL_001M"),
    PWRNWH3XL_001M("WHITE-WH", "MENS RN", "3XL", "PWRNWH3XL_001M"),
    PWRNWH4XL_001M("WHITE-WH", "MENS RN", "4XL", "PWRNWH4XL_001M"),
    PWRNWH5XL_001M("WHITE-WH", "MENS RN", "5XL", "PWRNWH5XL_001M"),
    PWRNBKXS_001M("WHITE-WH", "MENS RN", "XS", "PWRNWHXS_001M"), //Black
    PWRNBKS_001M("WHITE-WH", "MENS RN", "S", "PWRNWHS_001M"),
    PWRNBKM_001M("WHITE-WH", "MENS RN", "M", "PWRNWHM_001M"),
    PWRNBKL_001M("WHITE-WH", "MENS RN", "L", "PWRNWHL_001M"),
    PWRNBKXL_001M("WHITE-WH", "MENS RN", "XL", "PWRNWHXL_001M"),
    PWRNBK2XL_001M("WHITE-WH", "MENS RN", "2XL", "PWRNWH2XL_001M"),
    PWRNBK3XL_001M("WHITE-WH", "MENS RN", "3XL", "PWRNWH3XL_001M"),
    PWRNBK4XL_001M("WHITE-WH", "MENS RN", "4XL", "PWRNWH4XL_001M"),
    PWRNBK5XL_001M("WHITE-WH", "MENS RN", "5XL", "PWRNWH5XL_001M"),
    ;
    private final String color;
    private final String model;
    private final String size;
    private final String sku;
    private SKU(String color, String model, String size, String sku) {
        this.color = color;
        this.model = model;
        this.size = size;
        this.sku = sku;
    }
}
