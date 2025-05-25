package com.petshopmanagerapi.petshopmanagerapi.controller;

import com.petshopmanagerapi.petshopmanagerapi.model.user.User;
import com.petshopmanagerapi.petshopmanagerapi.security.AuthDTO;
import com.petshopmanagerapi.petshopmanagerapi.security.LoginResponseDTO;
import com.petshopmanagerapi.petshopmanagerapi.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthDTO authDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal(); // downcast para ter acesso a getId() e getRole()
        var token = tokenService.generationToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getId(), user.getRole(), token));
    }
}
