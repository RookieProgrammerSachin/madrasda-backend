package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.models.Design;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link Design} entity
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