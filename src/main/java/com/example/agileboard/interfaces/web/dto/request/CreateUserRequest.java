package com.example.agileboard.interfaces.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    private String email;
    private String name;
    private String password;
}
