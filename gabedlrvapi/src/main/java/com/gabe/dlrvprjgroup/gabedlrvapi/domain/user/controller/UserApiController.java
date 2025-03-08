package com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller;
// 회원 전용 API 컨트롤러

import com.gabe.dlrvprjgroup.gabedlrvapi.common.api.CommonApi;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.business.UserBusiness;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.user.controller.model.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {
    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public CommonApi<UserResponse> me(){
        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        var userId = requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
        var resp = userBusiness.me(Long.parseLong(userId.toString()));
        return CommonApi.ok(resp);
    }

}
