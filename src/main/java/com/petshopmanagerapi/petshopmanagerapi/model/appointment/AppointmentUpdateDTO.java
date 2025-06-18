package com.petshopmanagerapi.petshopmanagerapi.model.appointment;

import java.time.LocalDateTime;

public record AppointmentUpdateDTO(Long id, LocalDateTime dateAndTime, AppointmentStatus status) {
}
