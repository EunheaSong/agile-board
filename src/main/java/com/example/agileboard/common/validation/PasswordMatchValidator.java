package com.example.agileboard.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {
    
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        // 초기화 로직 (필요시)
    }
    
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return true;
        }
        
        try {
            // getClass().getMethod()를 사용하여 getter 메서드 호출
            String password = (String) object.getClass().getMethod("getPassword").invoke(object);
            String confirmPassword = (String) object.getClass().getMethod("getConfirmPassword").invoke(object);
            
            // null 체크
            if (password == null || confirmPassword == null) {
                return true; // null 값은 다른 검증에서 처리
            }
            
            return password.equals(confirmPassword);
        } catch (Exception e) {
            // 에러 발생 시 로그 출력
            System.err.println("PasswordMatchValidator error: " + e.getMessage());
            e.printStackTrace();
            return true; // 에러 발생 시 검증 통과 (다른 검증에서 처리)
        }
    }
}
