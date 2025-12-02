package com.lunghwan.user.application.response;

import com.lunghwan.user.domain.entity.Role;
import com.lunghwan.user.domain.entity.SocialType;
import com.lunghwan.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jasypt.encryption.StringEncryptor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private Role role;
    private Boolean socialUser;
    private SocialType socialType;
    private LocalDateTime createTime;

    /**
     * Entity → DTO 변환
     */
    public static UserResponse from(User user, StringEncryptor encryptor) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail().getValue())
                .userName(encryptor.decrypt(user.getEncUserName()))
                .phoneNumber(encryptor.decrypt(user.getEncPhoneNumber()))
                .role(user.getRole())
                .socialUser(user.getSocialUser())
                .socialType(user.getSocialType())
                .createTime(user.getCreateTime())
                .build();
    }

    /**
     * Entity → DTO 변환
     */
    public static UserResponse fromMasked(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .email(user.getEmail().getValue())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .socialUser(user.getSocialUser())
                .socialType(user.getSocialType())
                .createTime(user.getCreateTime())
                .build();
    }
}
