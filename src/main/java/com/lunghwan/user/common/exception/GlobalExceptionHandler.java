package com.lunghwan.user.common.exception;

import com.lunghwan.user.common.response.ApiResponse;
import com.lunghwan.user.common.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * UserApiException 처리
     */
    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserApiException(UserApiException e) {
        log.error("UserApiException: code={}, message={}, detail={}",
                e.getErrorCode().getCode(),
                e.getErrorCode().getMessage(),
                e.getDetail());

        ErrorResponse errorResponse = e.getDetail() != null ? ErrorResponse.of(e.getErrorCode(), e.getDetail()) : ErrorResponse.of(e.getErrorCode());

        return ResponseEntity
                .status(e.getErrorCode().getStatus())
                .body(ApiResponse.error(errorResponse));
    }

    /**
     * Validation 예외 처리 (@Valid 실패)
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(MethodArgumentNotValidException e) {
        log.error("Validation Exception: {}", e.getMessage());

        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.INVALID_INPUT_VALUE.getCode(),
                fieldError.getDefaultMessage(),
                fieldError.getField() + ": " + fieldError.getRejectedValue()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error(errorResponse));
    }

    /**
     * 모든 예외 처리
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        log.error("Unexpected Exception: ", e);

        ErrorResponse errorResponse = ErrorResponse.of(
                ErrorCode.INTERNAL_SERVER_ERROR,
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error(errorResponse));
    }
}