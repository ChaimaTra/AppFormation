/*package com.example.gdechetslabibup.validator;

import com.example.gdechetslabibup.dto.LoginRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginRequestV implements Validator{
   
     @Override
    public boolean supports(Class<?> clazz) {
        return LoginRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginRequest loginRequest = (LoginRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required");
    }
}
*/