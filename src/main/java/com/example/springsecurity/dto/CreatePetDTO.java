package com.example.springsecurity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePetDTO {

    private String name;
    private String gender;
    private String age;
    private Boolean neutered;
    private String category;
    private String subcategory;
    private Boolean goodWithDogs;
    private Boolean goodWithCats;
    private Boolean goodWithKids;
}
