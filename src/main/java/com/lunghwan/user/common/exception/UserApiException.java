package com.lunghwan.user.common.exception;

import lombok.Getter;

@Getter
public class UserApiException extends RuntimeException {

    private final ErrorCode errorCode;

    private final String detail;

    public UserApiException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = null;
    }

    public UserApiException(ErrorCode errorCode, String detail) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
