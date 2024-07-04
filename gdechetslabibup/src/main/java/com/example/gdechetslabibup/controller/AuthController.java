package com.example.gdechetslabibup.controller;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.core.AuthenticationException;

import com.example.gdechetslabibup.exception.DuplicateUserException;
import com.example.gdechetslabibup.exception.UserNotFoundException;
import com.example.gdechetslabibup.service.AuthenticationService;
import com.example.gdechetslabibup.service.JwtService;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.dto.LoginRequest;
import com.example.gdechetslabibup.dto.RegistrationRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Injecting required services
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    // Constructor for dependency injection
    public AuthController(AuthenticationService authenticationService,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // Endpoint for user login (sign-in)
    @PostMapping("/signin")

    public ResponseEntity<?> auth(Authentication authentication) {
        try {
            // Authentifier l'utilisateur
            LoginRequest authenticationUserDTO = authenticationService.login(authentication);

            // Générer le jeton JWT et le cookie JWT
            String jwtToken = jwtService.generateToken(authentication);
            ResponseCookie jwtCookie = jwtService.generateJwtCookie(jwtToken);

            // Ajouter le cookie JWT à l'en-tête de la réponse
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, jwtCookie.toString());

            // Retourner une réponse HTTP OK avec le DTO d'authentification et l'en-tête contenant le cookie JWT
            return ResponseEntity.ok().headers(headers).body(authenticationUserDTO);
        } catch (UserNotFoundException e) {
            // Gérer l'exception UserNotFoundException
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found or not authenticated");
        } catch (Exception e) {
            // Gérer les autres exceptions génériques
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }

   /*  @PostMapping("/signin")
    public ResponseEntity<LoginRequest> auth(Authentication authentication) {
        // Authenticate the user and generate the authenticated user DTO
        LoginRequest authenticationUserDTO = this.authenticationService.login(authentication);
        // Generate a JWT cookie
        ResponseCookie jwtCookie = jwtService.generateJwtCookie(jwtService.generateToken(authentication));
        // Return the response with the JWT cookie in the headers
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(authenticationUserDTO);
    }*/

    // Endpoint for user registration (sign-up)
    @PostMapping("/signup")
    public ResponseEntity<RegistrationRequest> register(@Valid @RequestBody RegistrationRequest registerUserDTO) throws DuplicateUserException {
        // Register the user and return the registered user DTO
        User user = authenticationService
                .register(RegistrationRequest.fromRegisterUserDTO(registerUserDTO, passwordEncoder));
        return ResponseEntity.ok()
                .body(RegistrationRequest.toRegisterUserDTO(user));
    }

    // Endpoint for user logout (sign-out)
    @PostMapping("/signout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        // Generate a clean JWT cookie to remove the existing one
        ResponseCookie jwtCookie = jwtService.getCleanJwtCookie();
        // Return the response with the clean JWT cookie in the headers
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .build();
    }
}


