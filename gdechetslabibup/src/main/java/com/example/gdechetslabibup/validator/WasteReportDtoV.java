package com.example.gdechetslabibup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.gdechetslabibup.dto.WasteReportDto;

@Component
public class WasteReportDtoV implements Validator {
  
     @Override
    public boolean supports(Class<?> clazz) {
        return WasteReportDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        WasteReportDto WasteReportDto = (WasteReportDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required", "description is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photoUrl", "field.required", "photoUrl is required");

       
    }
}
