package com.lunghwan.user.domain.service;

import com.lunghwan.user.common.exception.ErrorCode;
import com.lunghwan.user.common.exception.UserApiException;
import com.lunghwan.user.domain.entity.Password;
import com.lunghwan.user.domain.entity.User;
import com.lunghwan.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final StringEncryptor encryptor;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            @Qualifier("jasyptStringEncryptor") StringEncryptor encryptor
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.encryptor = encryptor;
    }

    /**
     * 회원가입 서비스
     * @param email 이메일
     * @param password 패스워드
     * @param confirmPassword 패스워드 확인
     * @param userName 사용자 이름
     * @param phoneNumber 핸드폰 번호
     * @return User
     */
    @Transactional
    public User signUp(String email, String password, String confirmPassword, String userName, String phoneNumber) {

        // 1. 이메일 중복 체크
        if (userRepository.existsByEmail(email)) {
            throw new UserApiException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 2. 비밀번호 확인
        if (!password.matches(confirmPassword)) {
            throw new UserApiException(ErrorCode.INVALID_PASSWORD_CONFIRM);
        }

        // 3. 비밀번호 검증
        Password.validateRawPassword(password);

        // 4. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(password);

        // 5. 저장
        User user = User.createGeneralUser(
                email,
                encodedPassword,
                userName,
                phoneNumber,
                encryptor
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
