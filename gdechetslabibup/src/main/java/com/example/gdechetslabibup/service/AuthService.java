
package com.example.gdechetslabibup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import com.example.restapicontactmanagement.exceptions.DuplicateUserException;
import com.example.gdechetslabibup.service.AuthenticationService;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.repos.UserRepository;
import com.example.gdechetslabibup.dto.LoginRequest;
import com.example.gdechetslabibup.exception.DuplicateUserException;
import com.example.gdechetslabibup.exception.UserNotFoundException;

@Service
public class AuthService implements AuthenticationService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    // Repository to handle User entity persistence
    private final UserRepository userRepository;

        @Autowired
    private PasswordEncoder passwordEncoder;

    // Constructor injection for UserRepository
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws DuplicateUserException {
        if (user == null) {
            return null;
        }
        try {
            // Save the user in the repository
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle uniqueness constraint violations
            throw new DuplicateUserException("User already exists");
        }
    }

  

  @Override
    public LoginRequest login(Authentication authentication) throws UserNotFoundException {
        logger.debug("Attempting user login...");

        // Vérifier que l'authentification n'est pas null et qu'elle contient les informations nécessaires
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.error("Authentication object is null or not authenticated");
            throw new UserNotFoundException("Authentication object is null or not authenticated");
        }
       // Récupérer l'utilisateur à partir de l'authentification
        User user = (User) authentication.getPrincipal();
        if (user == null) {
            logger.error("User not found in authentication object");

            throw new UserNotFoundException("User not found in authentication object");
        }
        logger.info("User logged in successfully: {}", user.getEmail());
       // Convertir l'utilisateur en LoginRequest DTO et le retourner
        return LoginRequest.toAuthenticationUserDTO(user);
    }
}