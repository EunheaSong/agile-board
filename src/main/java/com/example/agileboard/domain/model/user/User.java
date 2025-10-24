package com.example.agileboard.domain.model.user;

import com.example.agileboard.common.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String name;
    private String password;
    
    public static User create(String email, String name, String password) {
        validateEmail(email);
        validatePassword(password);
        validateName(name);
        return new User(null, email, name, password);
    }
    
    private static void validateEmail(String email) {
        if (email == null || !email.contains("@") || !email.contains(".") || email.length() <= 5) {
            throw new BusinessException("잘못된 이메일 형식입니다.");
        }
    }
    
    private static void validatePassword(String password) {
        if (password == null || password.length() < 8 || 
            !password.matches(".*[A-Z].*") || 
            !password.matches(".*[a-z].*") || 
            !password.matches(".*[0-9].*")) {
            throw new BusinessException("비밀번호는 8자 이상, 대소문자와 숫자를 포함해야 합니다.");
        }
    }
    
    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new BusinessException("이름은 필수입니다.");
        }
    }
}
