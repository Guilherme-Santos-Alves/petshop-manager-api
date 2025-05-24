package com.petshopmanagerapi.petshopmanagerapi.controller;

import com.petshopmanagerapi.petshopmanagerapi.model.user.*;
import com.petshopmanagerapi.petshopmanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
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
    public ResponseEntity<Void> create(@RequestBody UserDTO userDTO, Authentication authentication) {
        Set<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        boolean isAdmin = roles.contains("ROLE_ADMIN");
        boolean isUser = roles.contains("ROLE_USER");

        if (isUser && !isAdmin &&
                (userDTO.role() == UserRole.ADMIN || userDTO.role() == UserRole.VETERINARIAN)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        String crmv;
        String cleanCpf = userDTO.cpf().replaceAll("\\D", "");
        String cleanPhone = userDTO.phone().replaceAll("\\D", "");

        if (userDTO.role() != UserRole.VETERINARIAN) {
            crmv = "";
        } else {
            crmv = userDTO.crmv() != null ? userDTO.crmv() : "";
        }

        User user = new User(userDTO.name(), cleanCpf, cleanPhone, userDTO.email(),
                userDTO.address(), crmv, userDTO.role(), encryptedPassword);

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