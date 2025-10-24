package com.example.agileboard.infrastructure.adapter.persistence;

import com.example.agileboard.application.port.out.UserPersistencePort;
import com.example.agileboard.domain.model.user.User;
import com.example.agileboard.infrastructure.persistence.entity.UserEntity;
import com.example.agileboard.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserJpaRepository userJpaRepository;
    
    @Override
    public User save(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity savedEntity = userJpaRepository.save(entity);
        return savedEntity.toDomain();
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(UserEntity::toDomain);
    }
    
    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }
}
