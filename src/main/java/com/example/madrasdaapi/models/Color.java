package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Color {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String color;
     String hexValue;


}
