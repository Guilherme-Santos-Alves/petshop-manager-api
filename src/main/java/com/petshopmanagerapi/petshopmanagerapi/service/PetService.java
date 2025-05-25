package com.petshopmanagerapi.petshopmanagerapi.service;

import com.petshopmanagerapi.petshopmanagerapi.model.pet.Pet;
import com.petshopmanagerapi.petshopmanagerapi.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    PetRepository petRepository;

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public List<Pet> getAllPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwner_Id(ownerId);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePetById(Long id) {
        petRepository.deleteById(id);
    }
}