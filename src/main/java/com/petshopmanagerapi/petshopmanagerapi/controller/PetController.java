package com.petshopmanagerapi.petshopmanagerapi.controller;

import com.petshopmanagerapi.petshopmanagerapi.exception.ResourceNotFoundException;
import com.petshopmanagerapi.petshopmanagerapi.model.pet.*;
import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import com.petshopmanagerapi.petshopmanagerapi.service.PetService;
import com.petshopmanagerapi.petshopmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {
    @Autowired
    PetService petService;

    @Autowired
    UserService userService;

    @GetMapping
    public List<PetSummaryDTO> getAllPets() {
        List<Pet> pets = petService.getAllPets();

        return pets.stream()
                .map(pet -> new PetSummaryDTO(pet.getId(), pet.getName(), pet.getPetSpecies(), pet.getBreed(), pet.getGender(), pet.getOwner() != null ? pet.getOwner().getId() : null))
                .toList();
    }

    @GetMapping("/{id}")
    public PetResponseDTO getPetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado."));

        return new PetResponseDTO(
                pet.getId(), pet.getName(), pet.getPetSpecies(), pet.getBreed(), pet.getGender(), pet.getColor(), pet.getWeight(), pet.getHeight(), pet.getBirthDate(), pet.getOwner().getId()
        );
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetResponseDTO> getAllPetsByOwnerId(@PathVariable Long ownerId){
        User owner = userService.getUserById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("Dono não encontrado."));

        List<Pet> pets = petService.getAllPetsByOwnerId(ownerId);

        return pets.stream()
                .map( pet -> new PetResponseDTO(pet.getId(), pet.getName(), pet.getPetSpecies(), pet.getBreed(), pet.getGender(), pet.getColor(), pet.getWeight(), pet.getHeight(), pet.getBirthDate(), pet.getOwner().getId()))
                .toList();
    }

    @PostMapping
    public ResponseEntity<Void> createPet(@RequestBody PetDTO petDTO) {
        User owner = userService.getUserById(petDTO.ownerId())
                .orElseThrow(() -> new ResourceNotFoundException("Dono não encontrado."));

        Long ownerId = petDTO.ownerId();
        System.out.println(ownerId);

        Pet pet = new Pet(petDTO.name(), petDTO.petSpecies(), petDTO.breed(), petDTO.gender(), petDTO.color(), petDTO.weight(), petDTO.height(), petDTO.birthDate(), ownerId, owner);

        petService.save(pet);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePetById(@PathVariable Long id) {
        Pet pet = petService.getPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado."));

        petService.deletePetById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Void> updatePetById(@PathVariable Long id, @RequestBody PetUpdateDTO petUpdateDTO) {
        Pet pet = petService.getPetById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado."));

        pet.setName(petUpdateDTO.name());
        pet.setBreed(petUpdateDTO.breed());
        pet.setWeight(petUpdateDTO.weight());
        pet.setHeight(petUpdateDTO.height());
        petService.save(pet);

        return ResponseEntity.noContent().build();
    }
}