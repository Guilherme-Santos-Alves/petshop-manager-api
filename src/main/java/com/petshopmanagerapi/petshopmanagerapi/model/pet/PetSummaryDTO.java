package com.petshopmanagerapi.petshopmanagerapi.model.pet;

public record PetSummaryDTO(Long id, String name, PetSpecies species, String breed, PetGender gender, Long ownerId) {
}
