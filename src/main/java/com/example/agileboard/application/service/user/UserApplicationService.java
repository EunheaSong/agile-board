package com.example.agileboard.application.service.user;

import com.example.agileboard.application.port.in.CreateUserUseCase;
import com.example.agileboard.application.port.out.UserPersistencePort;
import com.example.agileboard.common.exception.BusinessException;
import com.example.agileboard.domain.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApplicationService implements CreateUserUseCase {
    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public User createUser(String email, String name, String password) {
        // 1. 이메일 중복 체크 (외부 의존성 사용)
        if (userPersistencePort.findByEmail(email).isPresent()) {
            throw new BusinessException("이미 존재하는 이메일입니다.");
        }
        
        // 2. User 엔티티의 정적 팩토리 메서드로 생성 (비즈니스 로직)
        User user = User.create(email, name, password);
        
        // 3. 비밀번호 암호화
        User encryptedUser = new User(user.getId(), user.getEmail(), user.getName(), 
                                    passwordEncoder.encode(user.getPassword()));
        
        // 4. 저장소에 저장
        return userPersistencePort.save(encryptedUser);
    }
}
