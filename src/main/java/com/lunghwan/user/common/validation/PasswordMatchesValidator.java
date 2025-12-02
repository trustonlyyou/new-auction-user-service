package com.lunghwan.user.common.validation;

import com.lunghwan.user.application.request.SignupRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignupRequest> {


    @Override
    public boolean isValid(SignupRequest signupRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(signupRequest)) {
            return true;
        }

        return signupRequest.getPassword() != null &&
                signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
    }
}
