package com.example.madrasdaapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mockup_images", schema = "madrasda")
public class MockupImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "mockup_id")
    private Mockup mockup;

    @OneToOne
    @JoinColumn(name = "color_id")
    private Color color;

    private String image;
}

