package com.example.agileboard.interfaces.web.controller;

import com.example.agileboard.application.port.in.CreateUserUseCase;
import com.example.agileboard.interfaces.web.dto.request.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody CreateUserRequest request) {
        createUserUseCase.createUser(
            request.getEmail(), 
            request.getName(), 
            request.getPassword()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }
}
