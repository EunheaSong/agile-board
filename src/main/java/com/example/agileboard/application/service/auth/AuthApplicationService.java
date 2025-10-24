package com.example.agileboard.application.service.auth;

import com.example.agileboard.application.port.in.LoginUseCase;
import com.example.agileboard.application.port.out.UserPersistencePort;
import com.example.agileboard.common.exception.BusinessException;
import com.example.agileboard.domain.model.user.User;
import com.example.agileboard.infrastructure.adapter.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthApplicationService implements LoginUseCase {
    
    private final UserPersistencePort userPersistencePort;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public String login(String email, String password) {
        Optional<User> userOpt = userPersistencePort.findByEmail(email);
        
        if (userOpt.isEmpty()) {
            throw new BusinessException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        
        User user = userOpt.get();
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        
        return jwtTokenProvider.generateToken(user.getEmail(), user.getId());
    }
}
