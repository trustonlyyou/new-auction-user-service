package com.lunghwan.user.presentation.api;

import com.lunghwan.user.application.facade.AuthFacade;
import com.lunghwan.user.application.request.LoginRequest;
import com.lunghwan.user.application.request.SignupRequest;
import com.lunghwan.user.application.response.LoginResponse;
import com.lunghwan.user.application.response.UserResponse;
import com.lunghwan.user.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthFacade authFacade;

    /**
     * 회원가입
     * @param request 회원가입 요청 파라미터
     * @return ApiResponse<UserResponse>
     */
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<UserResponse> signUp(@Valid @RequestBody SignupRequest request) {
        return ApiResponse.success(authFacade.signUp(request));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ApiResponse.success(authFacade.login(request));
    }


    /**
     * 내 정보 조회 (임시: userId를 파라미터로 받음)
     * TODO: 2025.12.10 [LungHwan] JWT 추가 후 토큰에서 userId 추출
     */
    @GetMapping("/me")
    public ApiResponse<UserResponse> getMyInfo(@RequestParam Long userId) {
        return ApiResponse.success(authFacade.getMyInfo(userId));
    }

}
