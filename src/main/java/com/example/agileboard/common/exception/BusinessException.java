package com.example.agileboard.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final String message;
    private final String code;
    
    public BusinessException(String message) {
        this(message, "BUSINESS_ERROR");
    }
}
