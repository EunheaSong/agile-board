package com.example.agileboard.application.port.in;

import com.example.agileboard.domain.model.user.User;

public interface LoginUseCase {
    String login(String email, String password);
}
