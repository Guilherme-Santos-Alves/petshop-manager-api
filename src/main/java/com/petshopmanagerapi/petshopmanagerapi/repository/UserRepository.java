package com.petshopmanagerapi.petshopmanagerapi.repository;

import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
