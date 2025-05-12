package com.guilhermesantos.petshop.service;

import com.guilhermesantos.petshop.dto.OwnerSummaryDTO;
import com.guilhermesantos.petshop.model.Owner;
import com.guilhermesantos.petshop.repository.OwnerRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;


    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAll() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }


}
