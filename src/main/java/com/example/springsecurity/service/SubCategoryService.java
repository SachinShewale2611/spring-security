package com.example.springsecurity.service;

import com.example.springsecurity.model.Category;
import com.example.springsecurity.model.SubCategory;
import com.example.springsecurity.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public SubCategory findOrCreateSubCategory(String subCategoryName, Category category) {
        return subCategoryRepository.findByNameAndCategory(subCategoryName, category)
                .orElseGet(() -> {
                    SubCategory newSubCategory = new SubCategory();
                    newSubCategory.setName(subCategoryName);
                    newSubCategory.setCategory(category);
                    return subCategoryRepository.save(newSubCategory);
                });
    }
}
