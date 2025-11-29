package com.lunghwan.user.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "서버 내부 오류가 발생했습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C002", "잘못된 입력값입니다."),

    // User
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "U001", "이미 존재하는 이메일입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U002", "사용자를 찾을 수 없습니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "U003", "올바르지 않은 이메일 형식입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U004", "올바르지 않은 비밀번호 형식입니다."),
    INVALID_PASSWORD_CONFIRM(HttpStatus.BAD_REQUEST, "U005", "비밀번호가 일치 하지 않습니다. 비밀번호를 확인해주세요."),
    INVALID_CREDENTIALS(HttpStatus.UNAUTHORIZED, "U006", "이메일 또는 비밀번호가 일치하지 않습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
