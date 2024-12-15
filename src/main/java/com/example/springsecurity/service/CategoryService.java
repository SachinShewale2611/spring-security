package com.example.springsecurity.service;

import com.example.springsecurity.model.Category;
import com.example.springsecurity.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findOrCreateCategory(String name) {
        return categoryRepository.findByName(name)
                .orElseGet(() -> {
                    Category category = new Category();
                    category.setName(name);
                    return categoryRepository.save(category);
                });
    }
}
