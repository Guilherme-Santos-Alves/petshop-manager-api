package com.petshopmanagerapi.petshopmanagerapi.controller;

import com.petshopmanagerapi.petshopmanagerapi.exception.ResourceNotFoundException;
import com.petshopmanagerapi.petshopmanagerapi.model.appointment.*;
import com.petshopmanagerapi.petshopmanagerapi.model.pet.Pet;
import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import com.petshopmanagerapi.petshopmanagerapi.service.AppointmentService;
import com.petshopmanagerapi.petshopmanagerapi.service.PetService;
import com.petshopmanagerapi.petshopmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    UserService userService;

    @Autowired
    PetService petService;


    @GetMapping
    public List<AppointmentResponseDTO> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();

        return appointments.stream()
                .map(appointment -> new AppointmentResponseDTO(appointment.getId(), appointment.getDate(), appointment.getTime(), appointment.getPet().getId(),
                        appointment.getVeterinarian().getId(), appointment.getStatus()))
                .toList();
    }

    @GetMapping("/{id}")
    public AppointmentResponseDTO getAppointmentById(@PathVariable Long id){
        Appointment appointment = appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado."));

        return new AppointmentResponseDTO(appointment.getId(), appointment.getDate(), appointment.getTime(), appointment.getPet().getId(),
                appointment.getVeterinarian().getId(), appointment.getStatus());
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Pet pet = petService.getPetById(appointmentDTO.petId())
                .orElseThrow(() -> new RuntimeException("Pet não encontrado"));

        User veterinarian = userService.getUserById(appointmentDTO.veterinarianId())
                .orElseThrow(() -> new RuntimeException("Veterinário não encontrado"));

        var status = AppointmentStatus.SCHEDULED;

        String time = appointmentDTO.dateAndTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        String date = appointmentDTO.dateAndTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        Appointment appointment = new Appointment(date, time, pet, veterinarian, status);

        appointmentService.save(appointment);

        return appointment;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAppointment(@PathVariable Long id, @RequestBody AppointmentUpdateDTO appointmentUpdateDTO){
        Appointment appointment = appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        // Verificar se o id do vet informado tem realmente a role de VET

        String date = appointmentUpdateDTO.dateAndTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String time = appointmentUpdateDTO.dateAndTime().format(DateTimeFormatter.ofPattern("HH:mm"));

        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setStatus(appointmentUpdateDTO.status());

        appointmentService.save(appointment);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
        Appointment appointment = appointmentService.getAppointmentById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        appointmentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}