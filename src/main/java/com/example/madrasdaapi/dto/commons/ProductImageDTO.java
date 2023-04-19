package com.example.madrasdaapi.dto.commons;

import com.example.madrasdaapi.models.Color;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDTO {
    private Long id;
    private String imageUrl;
    private Long color;
}
