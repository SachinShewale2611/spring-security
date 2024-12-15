package com.example.springsecurity.repository;

import com.example.springsecurity.model.Category;
import com.example.springsecurity.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findByNameAndCategory(String name, Category category);
}

