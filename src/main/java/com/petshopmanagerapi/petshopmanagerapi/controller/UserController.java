package com.petshopmanagerapi.petshopmanagerapi.controller;

import com.petshopmanagerapi.petshopmanagerapi.model.user.*;
import com.petshopmanagerapi.petshopmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserSummaryDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();

        return users.stream()
                .map(user -> new UserSummaryDTO(user.getId(), user.getName(), user.getPhone(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO) {
        String crmv;

        if (userDTO.role() != UserRole.VETERINARIAN) {
            crmv = "";
        } else {
            crmv = userDTO.crmv() != null ? userDTO.crmv() : "";
        }

        User user = new User(userDTO.name(), userDTO.cpf(), userDTO.phone(), userDTO.email(),
                userDTO.address(), crmv, userDTO.role(), userDTO.password());

        userService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable Long id, @RequestBody UserUpdateDTO userUpdateDTO) {
        User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        existingUser.setPhone(userUpdateDTO.phone());
        existingUser.setEmail(userUpdateDTO.email());
        existingUser.setAddress(userUpdateDTO.address());
        existingUser.setPassword(userUpdateDTO.password());

        userService.save(existingUser);

        return ResponseEntity.noContent().build();
    }
}