package com.lunghwan.user.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SocialType {
    KAKAO("카카오"),
    NAVER("네이버"),
    GOOGLE("구글")
    ;

    private final String description;
}
