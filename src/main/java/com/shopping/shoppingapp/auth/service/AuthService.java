package com.shopping.shoppingapp.auth.service;

import com.shopping.shoppingapp.auth.dto.AuthRequest;
import com.shopping.shoppingapp.auth.dto.AuthResponse;
import com.shopping.shoppingapp.auth.dto.UserDto;
import com.shopping.shoppingapp.auth.entities.User;
import com.shopping.shoppingapp.auth.repository.UserRepository;
import com.shopping.shoppingapp.auth.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(AuthRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);

        String token = JwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .message("Registration successful")
                .token(token)
                .user(UserDto.fromEntity(user))
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = JwtUtil.generateToken(user.getEmail());

        return AuthResponse.builder()
                .message("Login successful")
                .token(token)
                .user(UserDto.fromEntity(user))
                .build();
    }
}
