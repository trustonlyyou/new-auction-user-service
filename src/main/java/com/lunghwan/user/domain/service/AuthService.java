package com.lunghwan.user.domain.service;

import com.lunghwan.user.common.exception.ErrorCode;
import com.lunghwan.user.common.exception.UserApiException;
import com.lunghwan.user.domain.entity.User;
import com.lunghwan.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 인증
     * @param email 이메일
     * @param password 패스워드
     * @return User
     */
    public User authenticate(String email, String password) {
        // 1. 이메일로 사용자 조회
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserApiException(ErrorCode.USER_NOT_FOUND));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(password, user.getPassword().getValue())) {
            throw new UserApiException(ErrorCode.INVALID_CREDENTIALS);
        }

        return user;
    }
}
