package com.petshopmanagerapi.petshopmanagerapi.model.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum UserRole {
    USER(List.of("ROLE_USER")),
    VETERINARIAN(List.of("ROLE_VETERINARIAN", "ROLE_USER")),
    ADMIN(List.of("ROLE_ADMIN", "ROLE_USER"));

    private final List<String> authorities;

    UserRole(List<String> authorities) {
        this.authorities = authorities;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
