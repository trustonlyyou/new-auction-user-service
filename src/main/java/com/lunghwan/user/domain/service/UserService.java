package com.lunghwan.user.domain.service;

import com.lunghwan.user.common.exception.ErrorCode;
import com.lunghwan.user.common.exception.UserApiException;
import com.lunghwan.user.domain.entity.Password;
import com.lunghwan.user.domain.entity.User;
import com.lunghwan.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 서비스
     * @param email 이메일
     * @param encryptPassword 패스워드
     * @param maskedUserName 사용자 이름 (마스킹)
     * @param maskedPhoneNumber 핸드폰 번호 (마스킹)
     * @param encryptedUserName 사용자 이름(암호화)
     * @param encryptedPhoneNumber 핸드폰 번호(암호화)
     * @return
     */
    @Transactional
    public User signUp(String email, String encryptPassword,
                       String maskedUserName, String maskedPhoneNumber,
                       String encryptedUserName, String encryptedPhoneNumber) {

        // 1. 이메일 중복 체크
        if (userRepository.existsByEmail(email)) {
            throw new UserApiException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 2. 저장
        User user = User.createGeneralUser(
                email, encryptPassword,
                maskedUserName, maskedPhoneNumber,
                encryptedUserName, encryptedPhoneNumber
        );

        return userRepository.save(user);
    }

    /**
     * 이메일로 사용자 조회
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserApiException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * ID로 사용자 조회
     */
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserApiException(ErrorCode.USER_NOT_FOUND));
    }

}
