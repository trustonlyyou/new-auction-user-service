package com.lunghwan.user.infrastructure.persistence;

import com.lunghwan.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    /**
     * 이메일로 사용자 조회
     */
    Optional<User> findByEmail_Value(String email);

    /**
     * 이메일 중복 체크
     */
    boolean existsByEmail_Value(String email);

    /**
     * 소셜 ID로 사용자 조회
     */
    Optional<User> findBySocialId(String socialId);

}
