package com.petshopmanagerapi.petshopmanagerapi.model.appointment;

import com.petshopmanagerapi.petshopmanagerapi.model.pet.Pet;
import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ps_appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A data do agendamento é obrigatória.")
    private String date;

    @NotNull(message = "A hora do agendamento é obrigatória.")
    private String time;

    @NotNull(message = "O pet é obrigatório no agendamento.")
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @NotNull(message = "O veterinário é obrigatório no agendamento.")
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private User veterinarian;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do agendamento é obrigatório.")
    private AppointmentStatus status;

    public Appointment(String date, String time, Pet pet, User veterinarian, AppointmentStatus status) {
        this.date = date;
        this.time = time;
        this.pet = pet;
        this.veterinarian = veterinarian;
        this.status = status;
    }

    public Appointment() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public User getVeterinarian() {
        return veterinarian;
    }

    public void setVeterinarian(User veterinarian) {
        this.veterinarian = veterinarian;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}