package com.petshopmanagerapi.petshopmanagerapi.model.pet;

import java.time.LocalDate;

public record PetResponseDTO(Long id, String name, PetSpecies petSpecies, String breed, PetGender gender, String color, Double weight, Double height, LocalDate birthDate, Long ownerId) {
}
