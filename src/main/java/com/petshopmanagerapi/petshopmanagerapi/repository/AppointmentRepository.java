package com.petshopmanagerapi.petshopmanagerapi.repository;

import com.petshopmanagerapi.petshopmanagerapi.model.appointment.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
