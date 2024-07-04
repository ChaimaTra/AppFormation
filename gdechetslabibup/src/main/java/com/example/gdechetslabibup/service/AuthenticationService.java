package com.example.gdechetslabibup.service;

import org.springframework.security.core.Authentication;
import com.example.gdechetslabibup.model.User;
import com.example.gdechetslabibup.exception.DuplicateUserException;
import com.example.gdechetslabibup.dto.LoginRequest;
public interface AuthenticationService {
   
    User register(User user) throws DuplicateUserException;
    LoginRequest login(Authentication authentication);
}
