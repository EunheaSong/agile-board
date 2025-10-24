package com.example.agileboard.interfaces.web.controller;

import com.example.agileboard.application.port.in.LoginUseCase;
import com.example.agileboard.interfaces.web.dto.request.LoginRequest;
import com.example.agileboard.interfaces.web.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final LoginUseCase loginUseCase;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        String token = loginUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
