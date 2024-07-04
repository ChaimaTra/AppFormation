package com.example.gdechetslabibup.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.gdechetslabibup.dto.RecyclingInfo;
@Component
public class RecyclingInfoV implements Validator {
   
     @Override
    public boolean supports(Class<?> clazz) {
        return RecyclingInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RecyclingInfo RecyclingInfo = (RecyclingInfo) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required", "title is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "field.required", "content is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mediaUrl", "field.required", "mediaUrl is required");

       
    }
}
