package com.example.madrasdaapi.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * A DTO for the {@link Customer} entity
 */
@Getter
@Setter
public class CustomerDTO implements Serializable {
    private Long id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}