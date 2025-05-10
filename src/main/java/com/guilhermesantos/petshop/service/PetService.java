package com.guilhermesantos.petshop.service;

import com.guilhermesantos.petshop.model.Pet;
import com.guilhermesantos.petshop.repository.PetRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAll() {
        return petRepository.findAll(Sort.by(Sort.Order.asc("id")));
    }

    public Optional<Pet> getPetById(Long ownerId) {
        return petRepository.findById(ownerId);
    }

    public List<Pet> getPetsByOwnerId(Long ownerId) {
        return petRepository.findByOwner_Id(ownerId);
    }

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public boolean existsById(Long id) {
        return petRepository.existsById(id);
    }

    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
}
