package com.example.demo.validator;

import com.example.demo.repository.IuserRepository;
import com.example.demo.validator.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername,String> {
    @Autowired
    private  IuserRepository iuserRepository;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(iuserRepository == null)
            return true;
        return iuserRepository.findByUsername(username) == null;
    }
}
