package com.example.agileboard.application.port.in;

import com.example.agileboard.domain.model.user.User;

public interface CreateUserUseCase {
    User createUser(String email, String name, String password);
}
