package com.example.gdechetslabibup.dto;

import com.example.gdechetslabibup.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.gdechetslabibup.model.Role;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank(message = "firstname is required") String firstname,
        @NotBlank(message = "lastname is required") String lastname,
        @NotBlank(message = "Quartier is required") String quartier,
        @NotBlank(message = "email is required") @Email(message = "email format is not valid") String email,
        @NotBlank(message = "password is required")  @Size(min = 6, message = "Password must be at most 6 characters long") String password
        
        ) {
  
            public static User fromRegisterUserDTO(RegistrationRequest registerUserDTO, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .firstname(registerUserDTO.firstname())
                .lastname(registerUserDTO.lastname())
                .quartier(registerUserDTO.quartier())
                .email(registerUserDTO.email())
                .password(passwordEncoder.encode(registerUserDTO.password()))
                .role(Role.USER)  // Rôle par défaut défini ici
                .build();
        return user;
    }

    public static RegistrationRequest toRegisterUserDTO(User user) {
        return new RegistrationRequest(user.getFirstname(),
                user.getLastname(),
                user.getQuartier(),
                user.getEmail(),
                user.getPassword());
                
    }
}

