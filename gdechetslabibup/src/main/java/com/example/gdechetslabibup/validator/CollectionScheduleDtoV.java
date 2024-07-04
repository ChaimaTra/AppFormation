package com.example.gdechetslabibup.validator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.gdechetslabibup.dto.CollectionScheduleDto;
import com.example.gdechetslabibup.dto.WasteReportDto;

@Component
public class CollectionScheduleDtoV implements Validator {
 
      @Override
    public boolean supports(Class<?> clazz) {
        return CollectionScheduleDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CollectionScheduleDto CollectionScheduleDto = (CollectionScheduleDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "neighborhood", "field.required", "neighborhood is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "collectionDays", "field.required", "collectionDays is required");

       
    }
}
