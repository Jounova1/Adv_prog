package com.example.app.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.LoginRequest;
import com.example.app.dto.SignupRequest;
import com.example.app.model.User;
import com.example.app.service.AuthService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

@PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
    return authService.signup(request);
}
    
@PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}