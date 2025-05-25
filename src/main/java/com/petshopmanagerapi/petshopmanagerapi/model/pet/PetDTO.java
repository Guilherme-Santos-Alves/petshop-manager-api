package com.petshopmanagerapi.petshopmanagerapi.model.pet;

import java.time.LocalDate;

public record PetDTO(String name, PetSpecies petSpecies, String breed, PetGender gender, String color, Double weight, Double height, LocalDate birthDate, Long ownerId) {
}
