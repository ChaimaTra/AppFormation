/*package com.example.gdechetslabibup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.gdechetslabibup.dto.UserDto;

@Component
public class UserDtoV implements Validator {
      @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto UserDto = (UserDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "field.required", "Username is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required", "Password is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "field.required", "Email is required");

       
    }
}
*/