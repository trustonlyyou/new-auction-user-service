package com.lunghwan.user.domain.entity;

import com.lunghwan.user.common.exception.ErrorCode;
import com.lunghwan.user.common.exception.UserApiException;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "PASSWORD", nullable = false)
    private String value;

    public Password(String value) {
        this.value = value;
    }

    /**
     * 비밀번호 검증 (평문 비밀번호용)
     */
    public static void validateRawPassword(String rawPassword) {
        if (rawPassword == null || rawPassword.isBlank()) {
            throw new UserApiException(ErrorCode.INVALID_PASSWORD, "비밀번호는 필수입니다.");
        }
        if (rawPassword.length() < 8 || rawPassword.length() > 20) {
            throw new UserApiException(ErrorCode.INVALID_PASSWORD, "비밀번호는 8-20자여야 합니다.");
        }
        // TODO: 2025.11.19 [LungHwan] 추가 검증 로직 (영문, 숫자, 특수문자 포함 등)
    }
}
