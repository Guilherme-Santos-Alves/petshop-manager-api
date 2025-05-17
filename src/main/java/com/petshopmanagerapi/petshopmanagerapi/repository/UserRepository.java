package com.petshopmanagerapi.petshopmanagerapi.repository;

import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
