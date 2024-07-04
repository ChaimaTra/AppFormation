package com.example.gdechetslabibup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.gdechetslabibup.dto.ProfilDto;

@Component
public class ProfilDtoV  implements Validator{
      @Override
    public boolean supports(Class<?> clazz) {
        return ProfilDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProfilDto updateProfileRequest = (ProfilDto) target;

        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "field.required", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "field.required", "address is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "field.required", "phoneNumber is required");

    }
}
