package com.petshopmanagerapi.petshopmanagerapi.model.user;

public record UserDTO(String name, String cpf, String phone, String email, String address, String crmv, UserRole role, String password) {
}