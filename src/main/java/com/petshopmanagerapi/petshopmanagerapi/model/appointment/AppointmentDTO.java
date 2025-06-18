package com.petshopmanagerapi.petshopmanagerapi.model.appointment;

import java.time.LocalDateTime;

public record AppointmentDTO (LocalDateTime dateAndTime, Long petId, Long veterinarianId){
}
