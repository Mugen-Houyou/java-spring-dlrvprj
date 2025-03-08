package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller;
// 비회원 API 컨트롤러

import com.gabe.dlrvprjgroup.gabedlrvapi.common.api.CommonApi;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.controller.model.TokenResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.business.UserBusiness;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserRegisterRequest;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserSignInRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/open-api/user")
public class UserOpenApiController {

    private final UserBusiness userBusiness;

    // 신규 회원 가입
    @PostMapping("/register")
    public CommonApi<UserResponse> register(
            @Valid
            @RequestBody CommonApi<UserRegisterRequest> requestCommonApi
    ){
        var resp = userBusiness.register(requestCommonApi.getBody());
        return CommonApi.ok(resp);
    }

    // 로그인
    @PostMapping("/sign-in")
    public CommonApi<TokenResponse> signIn(
            @Valid
            @RequestBody CommonApi<UserSignInRequest> requestCommonApi
    ){
        var resp = userBusiness.signIn(requestCommonApi.getBody());
        return CommonApi.ok(resp);
    }

}
