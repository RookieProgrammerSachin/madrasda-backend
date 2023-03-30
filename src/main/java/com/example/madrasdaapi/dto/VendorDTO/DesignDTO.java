package com.example.madrasdaapi.dto.VendorDTO;

import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Design} entity
 */
@Getter
@Setter
public class DesignDTO implements Serializable {
     private Long id;
     private String theme;
     private String img;
     private String position;
     private String printType;
}