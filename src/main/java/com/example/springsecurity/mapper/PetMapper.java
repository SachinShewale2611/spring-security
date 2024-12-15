package com.example.springsecurity.mapper;

import com.example.springsecurity.dto.CreatePetDTO;
import com.example.springsecurity.dto.PetDTO;
import com.example.springsecurity.model.Pet;
import com.example.springsecurity.model.Category;
import com.example.springsecurity.model.SubCategory;
import com.example.springsecurity.service.CategoryService;
import com.example.springsecurity.service.SubCategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class PetMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    // Mapping CreatePetDTO to Pet
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategoryFromString")
    @Mapping(target = "subcategory", expression = "java(mapSubCategoryFromString(createPetDTO.getSubcategory(), createPetDTO.getCategory()))")
    public abstract Pet toEntity(CreatePetDTO createPetDTO);

    // Mapping Pet to PetDTO
    @Mapping(target = "category", source = "category", qualifiedByName = "mapCategoryToString")
    @Mapping(target = "subcategory", source = "subcategory", qualifiedByName = "mapSubCategoryToString")
    public abstract PetDTO toDTO(Pet pet);

    // Custom mapping methods

    @Named("mapCategoryFromString")
    protected Category mapCategoryFromString(String category) {
        return categoryService.findOrCreateCategory(category);
    }

    protected SubCategory mapSubCategoryFromString(String subcategory, String category) {
        Category categoryEntity = categoryService.findOrCreateCategory(category);
        return subCategoryService.findOrCreateSubCategory(subcategory, categoryEntity);
    }

    @Named("mapCategoryToString")
    protected String mapCategoryToString(Category category) {
        return category != null ? category.getName() : null;
    }

    @Named("mapSubCategoryToString")
    protected String mapSubCategoryToString(SubCategory subCategory) {
        return subCategory != null ? subCategory.getName() : null;
    }
}
