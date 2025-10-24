package com.example.agileboard.infrastructure.adapter.security;

import com.example.agileboard.domain.model.user.User;
import com.example.agileboard.application.port.out.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final UserPersistencePort userPersistencePort;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userPersistencePort.findByEmail(email);
        
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + email);
        }
        
        User foundUser = user.get();
        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getEmail())
                .password(foundUser.getPassword())
                .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")))
                .build();
    }
}
