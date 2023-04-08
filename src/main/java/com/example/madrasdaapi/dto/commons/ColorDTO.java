package com.example.madrasdaapi.dto.commons;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Color} entity
 */
@Getter
@Setter
public class ColorDTO implements Serializable {
    private Long id;
    private String color;
    private String hexValue;
    private List<String> images = new ArrayList<>();
    private List<SizeDTO> sizes = new ArrayList<>();

}