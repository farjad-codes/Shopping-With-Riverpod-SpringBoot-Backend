package com.shopping.shoppingapp.auth.controller;

import com.shopping.shoppingapp.auth.dto.AuthResponse;
import com.shopping.shoppingapp.auth.dto.LoginRequest;
import com.shopping.shoppingapp.auth.dto.RegisterRequest;
import com.shopping.shoppingapp.auth.service.AuthService;
import com.shopping.shoppingapp.auth.entities.User;
import com.shopping.shoppingapp.auth.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
