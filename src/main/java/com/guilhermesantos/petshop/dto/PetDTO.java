package com.guilhermesantos.petshop.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PetDTO {
    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Raça é obrigatória")
    private String breed;

    @NotNull(message = "Gênero é obrigatório")
    private String gender;

    @NotBlank(message = "Cor é obrigatória")
    private String color;

    @NotNull(message = "Peso é obrigatório")
    @DecimalMin(value = "0.1", message = "Peso deve ser maior que 0")
    private Double weight;

    @NotNull(message = "Altura é obrigatória")
    @DecimalMin(value = "1.0", message = "Altura deve ser maior que 0")
    private Double height;

    @Past(message = "Data de nascimento deve ser válida")
    private LocalDate birthDate;

    @NotNull(message = "ID do proprietário é obrigatório")
    private Long ownerId;

    public PetDTO(String name, String breed, String gender, String color, double weight, double height, LocalDate birthDate, Long ownerId) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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
}