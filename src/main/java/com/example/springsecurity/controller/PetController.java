package com.example.springsecurity.controller;

import com.example.springsecurity.dto.CreatePetDTO;
import com.example.springsecurity.dto.PetDTO;
import com.example.springsecurity.mapper.PetMapper;
import com.example.springsecurity.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pets")
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    @Autowired
    public PetController(PetService petService, PetMapper petMapper) {
        this.petService = petService;
        this.petMapper = petMapper;
    }

    // GET all pets
    @GetMapping
    public List<PetDTO> getAllPets() {
        return petService.getAllPets();
    }

    // GET pet by ID
    @GetMapping("/{id}")
    public PetDTO getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    // CREATE a new pet
    @PostMapping
    public ResponseEntity<PetDTO> createPet(@RequestBody @Valid CreatePetDTO createPetDTO) {
        PetDTO createdPet = petService.createPet(createPetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPet);
    }

    // UPDATE an existing pet
    @Operation(summary = "Update a pet by ID")
    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(
            @Parameter(description = "ID of the pet to be updated") @PathVariable Long id,
            @RequestBody @Valid CreatePetDTO createPetDTO) {

        PetDTO updatedPet = petService.updatePet(id, createPetDTO);
        return ResponseEntity.ok(updatedPet);
    }

    // DELETE a pet by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
