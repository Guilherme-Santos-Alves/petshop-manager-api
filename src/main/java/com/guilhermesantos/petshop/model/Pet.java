package com.guilhermesantos.petshop.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ps_pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String breed;
    private String gender;
    private String color;

    private Double weight;
    private Double height;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Transient
    private Long ownerId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    public Pet(String name, String breed, String gender, String color, Double weight, Double height, LocalDate birthDate, Long ownerId, Owner owner) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.color = color;
        this.weight = weight;
        this.height = height;
        this.birthDate = birthDate;
        this.ownerId = ownerId;
        this.owner = owner;
    }

    public Pet () {

    }

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}