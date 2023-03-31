package com.example.madrasdaapi.dto.VendorDTO;

import com.example.madrasdaapi.dto.VendorDTO.VendorDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 * A DTO for the {@link Mockup} entity
 */
/** TODO
 * What is the use of quantity here
 */
@Getter
@Setter
public class MockupDTO implements Serializable {
     private Long id;
     private String imgPath;
     private String name;
     private String color;
     private String size;
     private Integer quantity;
     private String model;
     private String type;
     private String category;
     private VendorDTO vendor;
}