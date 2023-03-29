package com.example.madrasdaapi.dto.VendorDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.madrasdaapi.models.Design} entity
 */
@Data
public class DesignDTO implements Serializable {
     private Long id;
     private String theme;
     private String img;
     private String position;
     private String printType;
}