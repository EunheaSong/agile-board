package com.example.agileboard.interfaces.web.controller;

import com.example.agileboard.application.port.in.CreateUserUseCase;
import com.example.agileboard.interfaces.web.dto.request.CreateUserRequest;
import com.example.agileboard.interfaces.web.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request) {
        var user = createUserUseCase.createUser(
            request.getEmail(), 
            request.getName(), 
            request.getPassword()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user));
    }
}
