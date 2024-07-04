package com.example.gdechetslabibup.dto;

import com.example.gdechetslabibup.model.User;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;



public record LoginRequest(
    Long id,
    String email,
    List<String> roles) {
public static LoginRequest toAuthenticationUserDTO(User user) {
    List<String> roles = user.getRole().getAuthorities()
            .stream()
            .map(SimpleGrantedAuthority::getAuthority)
            .toList();
    return new LoginRequest(user.getId(), user.getEmail(), roles);
}
}


