package com.petshopmanagerapi.petshopmanagerapi.security;

import com.petshopmanagerapi.petshopmanagerapi.model.user.UserRole;

public record LoginResponseDTO(Long id, UserRole role, String token) {
}
