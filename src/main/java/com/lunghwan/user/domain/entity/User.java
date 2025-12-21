package com.lunghwan.user.domain.entity;

import com.lunghwan.user.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.jasypt.encryption.StringEncryptor;

@Entity
@Table(
        name = "USERS",
        indexes = {
                @Index(name = "idx_email", columnList = "EMAIL")
        }
)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사용자 이메일 (이메일로 로그인 시도)
     */
    @Embedded
    private Email email;

    /**
     * 비밀번호
     */
    @Embedded
    private Password password;

    /**
     * 마스킹 이름
     */
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    /**
     * 암호화 된 이름
     */
    @Column(name = "ENC_USER_NAME", nullable = false)
    private String encUserName;

    /**
     * 핸드폰 번호 마스킹
     */
    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    /**
     * 암호화 된 핸드폰 번호
     */
    @Column(name = "ENC_PHONE_NUMBER", nullable = false)
    private String encPhoneNumber;

    /**
     * 소셜 로그인 여부 (true: 소셜 계정)
     */
    @Column(name = "SOCIAL_USER", nullable = false)
    private Boolean socialUser = Boolean.FALSE;

    /**
     * 소셜 타입 예: KAKAO, NAVER, GOOGLE
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "SOCIAL_TYPE")
    private SocialType socialType;

    /**
     * 소셜 제공자가 부여한 고유 id
     */
    @Column(name = "SOCIAL_ID", length = 255)
    private String socialId;

    /**
     * 사용자 Role
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    /**
     * 계정 사용 유무
     */
    @Column(name = "USE_YN", nullable = false)
    private Boolean useYn = Boolean.TRUE;

    /**
     * 사용자 생성 (일반사용자)
     * @param email 이메일
     * @param encryptPassword 패스워드(암호화)
     * @param maskedUserName 사용자 이름(마스킹)
     * @param maskedPhoneNumber 핸드폰 번호(마스킹)
     * @param encryptedUserName 사용자 이름(마스킹)
     * @param encryptedPhoneNumber 핸드폰 번호(마스킹)
     * @return User
     */
    public static User createGeneralUser(
            String email, String encryptPassword,
            String maskedUserName, String maskedPhoneNumber,
            String encryptedUserName, String encryptedPhoneNumber
    ) {
        return User.builder()
                .email(new Email(email))
                .password(new Password(encryptPassword))
                .userName(maskedUserName)
                .encUserName(encryptedUserName)
                .phoneNumber(maskedPhoneNumber)
                .encPhoneNumber(encryptedPhoneNumber)
                .socialUser(false)
                .role(Role.USER)
                .useYn(true) // 가입 즉시 활성
                .build();
    }
}
