package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sizes")
public class Size {
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private Long id;

          @Column(name = "size_type")
          private String sizeType;

          @Column
          private int chest;

          @Column
          private int length;

          @Column
          private int shoulder;

          @Column
          private int sleeve;

          @ManyToOne
          @JoinColumn(name = "mockup_id")
          private Mockup mockup;

     }



