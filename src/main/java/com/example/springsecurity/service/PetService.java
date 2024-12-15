package com.example.springsecurity.service;

import com.example.springsecurity.dto.CreatePetDTO;
import com.example.springsecurity.dto.PetDTO;
import com.example.springsecurity.mapper.PetMapper;
import com.example.springsecurity.model.Category;
import com.example.springsecurity.model.Pet;
import com.example.springsecurity.model.SubCategory;
import com.example.springsecurity.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PetService {

    private final PetMapper petMapper;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final PetRepository petRepository;

    @Autowired
    public PetService(PetMapper petMapper, CategoryService categoryService, SubCategoryService subCategoryService, PetRepository petRepository) {
        this.petMapper = petMapper;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.petRepository = petRepository;
    }

    // CREATE a new pet
    public PetDTO createPet(CreatePetDTO createPetDTO) {
        // Fetch or create Category
        Category category = categoryService.findOrCreateCategory(createPetDTO.getCategory());

        // Fetch or create SubCategory
        SubCategory subCategory = null;
        if (createPetDTO.getSubcategory() != null && !createPetDTO.getSubcategory().isEmpty()) {
            subCategory = subCategoryService.findOrCreateSubCategory(createPetDTO.getSubcategory(), category);
        }

        // Map DTO to Entity
        Pet pet = petMapper.toEntity(createPetDTO);
        pet.setCategory(category);
        pet.setSubcategory(subCategory);

        // Save to repository
        pet = petRepository.save(pet);

        // Return as DTO
        return petMapper.toDTO(pet);
    }

    // READ all pets
    public List<PetDTO> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(petMapper::toDTO)
                .collect(Collectors.toList());
    }

    // READ pet by ID
    public PetDTO getPetById(Long id) {
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + id));
        return petMapper.toDTO(pet);
    }

    // UPDATE an existing pet
    public PetDTO updatePet(Long id, CreatePetDTO createPetDTO) {
        // Find existing Pet
        Pet existingPet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found with ID: " + id));

        // Fetch or create Category
        Category category = categoryService.findOrCreateCategory(createPetDTO.getCategory());

        // Fetch or create SubCategory
        SubCategory subCategory = null;
        if (createPetDTO.getSubcategory() != null && !createPetDTO.getSubcategory().isEmpty()) {
            subCategory = subCategoryService.findOrCreateSubCategory(createPetDTO.getSubcategory(), category);
        }

        // Map DTO to Entity
        Pet updatedPet = petMapper.toEntity(createPetDTO);
        updatedPet.setId(existingPet.getId());
        updatedPet.setCategory(category);
        updatedPet.setSubcategory(subCategory);

        // Save updated entity
        updatedPet = petRepository.save(updatedPet);

        // Return as DTO
        return petMapper.toDTO(updatedPet);
    }

    // DELETE a pet by ID
    public void deletePet(Long id) {
        if (!petRepository.existsById(id)) {
            throw new RuntimeException("Pet not found with ID: " + id);
        }
        petRepository.deleteById(id);
    }
}
