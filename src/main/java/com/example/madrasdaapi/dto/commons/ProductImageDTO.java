package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.models.Color;
import lombok.Data;

@Data
public class ProductImageDTO {
    private Long id;
    private String imageUrl;
    private Long color;
}
