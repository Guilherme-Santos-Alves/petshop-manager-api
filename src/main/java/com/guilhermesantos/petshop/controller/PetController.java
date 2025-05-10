package com.guilhermesantos.petshop.controller;

import com.guilhermesantos.petshop.dto.PetDTO;
import com.guilhermesantos.petshop.dto.PetResponseDTO;
import com.guilhermesantos.petshop.dto.PetSummaryDTO;
import com.guilhermesantos.petshop.model.Owner;
import com.guilhermesantos.petshop.model.Pet;
import com.guilhermesantos.petshop.service.OwnerService;
import com.guilhermesantos.petshop.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<PetSummaryDTO> getAll() {
        List<Pet> pets = petService.getAll();

        return pets.stream()
                .map(pet -> new PetSummaryDTO(pet.getId(), pet.getName(), pet.getOwner() != null ? pet.getOwner().getId() : null))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<PetResponseDTO> getPetById(@PathVariable Long id) {
        return petService.getPetById(id)
                .map(pet -> new PetResponseDTO(pet.getId(), pet.getName(), pet.getBreed(), pet.getGender(),
                        pet.getColor(), pet.getWeight(), pet.getHeight(), pet.getBirthDate(), pet.getOwner().getId()));
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetResponseDTO> getPetByOwnerId(@PathVariable Long ownerId) {
        List<Pet> pets = petService.getPetsByOwnerId(ownerId);

        return pets.stream()
                .map( pet -> new PetResponseDTO(pet.getId(), pet.getName(), pet.getBreed(), pet.getGender(), pet.getColor(), pet.getWeight(), pet.getHeight(), pet.getBirthDate(), pet.getOwner().getId()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Void> createPet(@Valid @RequestBody PetDTO petDTO) {
        Owner owner = ownerService.getOwnerById(petDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Pet pet = new Pet(petDTO.getName(), petDTO.getBreed(), petDTO.getGender(), petDTO.getColor(),
                petDTO.getWeight(), petDTO.getHeight(), petDTO.getBirthDate(), owner.getId(), owner);

        petService.save(pet);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePet(@PathVariable Long id, @RequestBody PetDTO petDTO) {
        Pet petExistente = petService.getPetById(id)
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        Owner owner = ownerService.getOwnerById(petDTO.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner não encontrado"));

        petExistente.setName(petDTO.getName());
        petExistente.setBreed(petDTO.getBreed());
        petExistente.setGender(petDTO.getGender());
        petExistente.setColor(petDTO.getColor());
        petExistente.setWeight(petDTO.getWeight());
        petExistente.setHeight(petDTO.getHeight());
        petExistente.setBirthDate(petDTO.getBirthDate());
        petExistente.setOwner(owner);

        petService.save(petExistente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}