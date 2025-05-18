package com.petshopmanagerapi.petshopmanagerapi.model.user;

public record UserSummaryDTO(Long id, String name, String phone, String email, UserRole role) {
}
