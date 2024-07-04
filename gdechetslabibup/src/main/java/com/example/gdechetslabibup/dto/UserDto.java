/*package com.example.gdechetslabibup.dto;




import java.util.Set;
import java.util.stream.Collectors;

import com.example.gdechetslabibup.model.RoleName;
import com.example.gdechetslabibup.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String email;
    private String password;
    private Set<RoleName> roles;
    private String quartier;
     public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream()
                        .map(role -> role.getRoleName())
                        .collect(Collectors.toSet()))
                .quartier(user.getQuartier())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .email(this.email)
                .quartier(this.quartier)
               // .password(this.password)
                .build();
    }
}*/