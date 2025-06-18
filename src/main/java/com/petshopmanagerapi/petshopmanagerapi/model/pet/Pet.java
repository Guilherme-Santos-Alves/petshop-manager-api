package com.petshopmanagerapi.petshopmanagerapi.model.pet;

import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

@Entity
@Table(name = "ps_pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do pet é obrigatório.")
    private String name;

    @NotNull(message = "A espécie do pet é obrigatória.")
    @Enumerated(EnumType.STRING)
    private PetSpecies petSpecies;

    @NotBlank(message = "A raça do pet é obrigatória.")
    private String breed;

    @NotNull(message = "O gênero do pet é obrigatório.")
    @Enumerated(EnumType.STRING)
    private PetGender gender;

    @NotBlank(message = "A cor do pet é obrigatória.")
    private String color;

    @NotNull(message = "O peso do pet é obrigatório.")
    @Positive(message = "O peso deve ser um valor positivo.")
    private Double weight;

    @NotNull(message = "A altura do pet é obrigatória.")
    @Positive(message = "A altura deve ser um valor positivo.")
    private Double height;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @PastOrPresent(message = "A data de nascimento não pode ser no futuro.")
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Transient
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @NotNull(message = "O dono do pet é obrigatório.")
    private User owner;

    public Pet(String name, PetSpecies petSpecies, String breed, PetGender gender, String color, Double weight, Double height, LocalDate birthDate, Long ownerId, User owner) {
        this.name = name;
        this.petSpecies = petSpecies;
        this.breed = breed;
        this.gender = gender;
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
        this.owner = owner;
    }

    public Pet() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetSpecies getPetSpecies() {
        return petSpecies;
    }

    public void setPetSpecies(PetSpecies petSpecies) {
        this.petSpecies = petSpecies;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public PetGender getGender() {
        return gender;
    }

    public void setGender(PetGender gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}