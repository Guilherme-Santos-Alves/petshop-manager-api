package com.guilhermesantos.petshop.controller;

import com.guilhermesantos.petshop.dto.OwnerDTO;
import com.guilhermesantos.petshop.dto.OwnerSummaryDTO;
import com.guilhermesantos.petshop.dto.PetSummaryDTO;
import com.guilhermesantos.petshop.dto.UpdateOwnerDTO;
import com.guilhermesantos.petshop.model.Owner;
import com.guilhermesantos.petshop.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<OwnerSummaryDTO> getAllOwners() {
        List<Owner> owners = ownerService.getAll();

        return owners.stream()
                .map(owner -> new OwnerSummaryDTO(owner.getId(), owner.getName(), owner.getPhone(), owner.getEmail()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Owner> getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PostMapping
    public ResponseEntity<Void> create (@RequestBody OwnerDTO ownerDTO){
        Owner owner = new Owner(ownerDTO.getName(), ownerDTO.getCpf(), ownerDTO.getPhone(), ownerDTO.getEmail(),
                ownerDTO.getPassword(), ownerDTO.getAddress());

        ownerService.save(owner);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOwner(@PathVariable Long id, @RequestBody UpdateOwnerDTO updateOwnerDTO) {
        Owner ownerExistente = getOwnerById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        ownerExistente.setPhone(updateOwnerDTO.getPhone());
        ownerExistente.setEmail(updateOwnerDTO.getEmail());
        ownerExistente.setAddress(updateOwnerDTO.getAddress());
        ownerExistente.setPassword(updateOwnerDTO.getPassword());

        ownerService.save(ownerExistente);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Long id) {
        ownerService.deleteById(id);
    }
}