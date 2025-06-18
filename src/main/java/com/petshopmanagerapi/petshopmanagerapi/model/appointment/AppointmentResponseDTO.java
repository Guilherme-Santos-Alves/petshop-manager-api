package com.petshopmanagerapi.petshopmanagerapi.model.appointment;

public record AppointmentResponseDTO(Long id, String date, String time, Long petId, Long vetId, AppointmentStatus status) {
}
