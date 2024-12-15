package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class PetDTO {
    private Long id;
    private String name;
    private String gender;
    private Boolean neutered;
    private String category;
    private String subcategory;
    private Boolean goodWithDogs;
    private Boolean goodWithCats;
    private Boolean goodWithKids;
}


