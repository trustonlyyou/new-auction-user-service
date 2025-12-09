package com.lunghwan.user.common.response;

import com.lunghwan.user.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ErrorResponse {

    private final String code;
    private final String message;
    private final String details;
    private final LocalDateTime timestamp;

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                null,
                LocalDateTime.now()
        );
    }

    public static ErrorResponse of(ErrorCode errorCode, String detail) {
        return new ErrorResponse(
                errorCode.getCode(),
                errorCode.getMessage(),
                detail,
                LocalDateTime.now()
        );
    }

    /**
     * Validation 에러용
     */
    public static ErrorResponse of(String code, String message, String detail) {
        return new ErrorResponse(
                code,
                message,
                detail,
                LocalDateTime.now()
        );
    }
}
