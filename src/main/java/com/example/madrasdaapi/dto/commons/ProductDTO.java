package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.models.Color;
import com.example.madrasdaapi.models.Size;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Float baseprice;
    private Float discount;
    private Float shipping;
    private Float total;
    private Float profit;
    private Float tax;
    private List<Color> colors;
    private List<Size> sizes;
    private List<ProductImageDTO> images;
}
