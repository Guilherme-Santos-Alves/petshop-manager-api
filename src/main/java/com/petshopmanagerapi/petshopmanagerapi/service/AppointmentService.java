package com.petshopmanagerapi.petshopmanagerapi.service;

import com.petshopmanagerapi.petshopmanagerapi.model.appointment.Appointment;
import com.petshopmanagerapi.petshopmanagerapi.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Long id){
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteById(Long id){
        appointmentRepository.deleteById(id);
    }

}