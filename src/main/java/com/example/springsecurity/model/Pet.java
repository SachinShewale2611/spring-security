package com.example.springsecurity.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pets")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String age;
    private Boolean neutered;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subcategory_id")
    private SubCategory subcategory;

    private Boolean goodWithDogs;
    private Boolean goodWithCats;
    private Boolean goodWithKids;

    // Getters and Setters
}

