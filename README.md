# 👤 User Service - New Auction

> **사용자 인증 및 관리 서비스** (OAuth 2.0, JWT)

---

## 📌 서비스 개요

User Service는 New Auction 시스템의 **사용자 인증 및 관리**를 담당하는 마이크로서비스입니다.

### 주요 기능
- ✅ 회원가입 / 로그인
- ✅ OAuth 2.0 소셜 로그인 (Kakao)
- ✅ JWT 기반 인증/인가
- ✅ 사용자 정보 암호화 (Jasypt)
- ✅ 사용자 정보 조회/수정

---

## 🛠️ 기술 스택

### Core
- **Language**: Java 21
- **Framework**: Spring Boot 3.5.0
- **Architecture**: DDD (Domain Driven Design) + Facade Pattern

### Data
- **ORM**: Spring Data JPA
- **Query**: QueryDSL 5.1.0
- **Database**: H2 (개발), MySQL 8.0 (운영)

### Security
- **Auth**: Spring Security
- **OAuth 2.0**: Kakao Login
- **Token**: JWT (jjwt 0.12.6)
- **Encryption**: Jasypt (AES-256)

### Microservice
- **Service Discovery**: Netflix Eureka Client
- **Config**: Spring Cloud Config (예정)

### Cache & Session
- **Redis**: Redisson 3.37.0

---

## 📊 API 명세