package com.example.gdechetslabibup.validator;


import com.example.gdechetslabibup.dto.RegistrationRequest;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class RegistrationRequestV implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return RegistrationRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistrationRequest registrationRequest = (RegistrationRequest) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "Email is required");

       
    }
}
