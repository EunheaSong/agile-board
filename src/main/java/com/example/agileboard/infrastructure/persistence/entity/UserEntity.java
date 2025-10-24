package com.example.agileboard.infrastructure.persistence.entity;

import com.example.agileboard.domain.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String password;
    
    public static UserEntity fromDomain(User user) {
        return new UserEntity(user.getId(), user.getEmail(), user.getName(), user.getPassword());
    }
    
    public User toDomain() {
        return new User(id, email, name, password);
    }
}
