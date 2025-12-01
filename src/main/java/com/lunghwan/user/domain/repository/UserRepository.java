package com.lunghwan.user.domain.repository;

import com.lunghwan.user.domain.entity.User;

import java.util.Optional;

/**
 * 사용자 Repository 인터페이스 (Domain Layer)
 * - 구현체는 Infrastructure Layer에 존재
 */
public interface UserRepository {

    /**
     * 사용자 저장
     */
    User save(User user);

    /**
     * ID로 사용자 조회
     */
    Optional<User> findById(Long id);

    /**
     * 이메일로 사용자 조회
     */
    Optional<User> findByEmail(String email);

    /**
     * 이메일 중복 체크
     */
    boolean existsByEmail(String email);

    /**
     * 소셜 ID로 사용자 조회
     */
    Optional<User> findBySocialId(String socialId);
}
