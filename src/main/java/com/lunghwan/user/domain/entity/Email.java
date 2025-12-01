package com.lunghwan.user.domain.entity;

import com.lunghwan.user.common.exception.ErrorCode;
import com.lunghwan.user.common.exception.UserApiException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    @Column(name = "EMAIL", nullable = false, unique = true, length = 100)
    private String value;

    public Email(String value) {
        validate(value);
        this.value = value;
    }

    private void validate(String value) {
        if (!StringUtils.hasText(value)) {
            throw new UserApiException(ErrorCode.INVALID_EMAIL, "이메일은 필수입니다.");
        }

        if (!EMAIL_PATTERN.matcher(value).matches()) {
            throw new UserApiException(ErrorCode.INVALID_EMAIL, "올바른 이메일 형식이 아닙니다.");
        }
    }
    
}
