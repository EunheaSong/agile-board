package com.example.agileboard.application.port.out;

import com.example.agileboard.domain.model.user.User;
import java.util.Optional;

public interface UserPersistencePort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
