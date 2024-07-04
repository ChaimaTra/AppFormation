//package com.example.gdechetslabibup.config;
/* 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/appgdechets/labibUp/api/**").permitAll() // Permettre l'accès public à tous les endpoints sous /appgdechets/labibUp/api/
                    .requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permettre l'accès public à Swagger UI et aux API docs
                    .anyRequest().authenticated()
            )
            .httpBasic(); // ou .formLogin() si vous utilisez la connexion par formulaire

        return http.build();
    }
}
*//* 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
// @EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for APIs
            .authorizeHttpRequests((requests) -> requests
                // .requestMatchers("/api/auth/**").permitAll()
                // .anyRequest().authenticated()
                //.requestMatchers("/reclamationAdmin").hasAuthority("ADMIN")
                .anyRequest().permitAll() // Allow all paths without authentication
            );
       

        return http.build();
    }


   
}*/    
