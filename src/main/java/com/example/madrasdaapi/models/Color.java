package com.example.madrasdaapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Color {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String color;
     String hexValue;
}
