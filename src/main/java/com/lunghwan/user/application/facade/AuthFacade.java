package com.lunghwan.user.application.facade;

import com.lunghwan.user.application.request.LoginRequest;
import com.lunghwan.user.application.request.SignupRequest;
import com.lunghwan.user.application.response.LoginResponse;
import com.lunghwan.user.application.response.UserResponse;
import com.lunghwan.user.domain.entity.User;
import com.lunghwan.user.domain.service.AuthService;
import com.lunghwan.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthFacade {

    private final UserService userService;
    private final AuthService authService;
    private final StringEncryptor stringEncryptor;

    public AuthFacade(UserService userService, AuthService authService, @Qualifier("jasyptStringEncryptor") StringEncryptor stringEncryptor) {
        this.userService = userService;
        this.authService = authService;
        this.stringEncryptor = stringEncryptor;
    }


    /**
     * 회원가입
     * @param request 회원 가입 요청 정보
     * @return UserResponse
     */
    @Transactional
    public UserResponse signUp(SignupRequest request) {
        User user = userService.signUp(
                request.getEmail(),
                request.getPassword(), request.getConfirmPassword(),
                request.getUserName(),
                request.getPhoneNumber());

        return UserResponse.from(user, stringEncryptor);
    }


    /**
     * 로그인
     * @param request 로그인 요청 정보
     * @return LoginResponse
     */
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        // 1. 인증 처리
        User user = authService.authenticate(
                request.getEmail(),
                request.getPassword()
        );

        // TODO: 2025.12.09 [LungHwan] JWT 토큰 발급

        // 임시: 더미 토큰 반환
        return LoginResponse.of(
                "temporary-access-token",
                "temporary-refresh-token",
                3600000L
        );
    }

    /**
     * 내 정보 조회
     * @param userId 사용자 ID (PK)
     * @return UserResponse
     */
    @Transactional(readOnly = true)
    public UserResponse getMyInfo(Long userId) {
        User user = userService.findById(userId);
        return UserResponse.from(user, stringEncryptor);
    }
}
