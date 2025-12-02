package com.lunghwan.user.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^01[0-9]-?[0-9]{3,4}-?[0-9]{4}$");

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {

        if (!StringUtils.hasText(phoneNumber)) {
            return false;
        }

        String cleanedPhone = phoneNumber.replaceAll("-", "");

        // 11자리 숫자 체크
        if (cleanedPhone.length() != 11) {
            return false;
        }

        // 010, 011, 016, 017, 018, 019로 시작하는지 체크
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}
