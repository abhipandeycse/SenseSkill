package com.senseskill.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean verified;

    private String verificationCode;
    
    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @Column(name = "reset_token_expiration")
    private LocalDateTime resetTokenExpiration;
}
