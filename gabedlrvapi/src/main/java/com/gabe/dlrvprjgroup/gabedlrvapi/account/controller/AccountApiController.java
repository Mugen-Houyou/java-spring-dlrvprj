package com.gabe.dlrvprjgroup.gabedlrvapi.account.controller;

import com.gabe.dlrvprjgroup.gabedlrvapi.account.model.AccountMeResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.api.CommonApi;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.UserErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvdb.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public CommonApi< AccountMeResponse> me(){
        var response = AccountMeResponse.builder()
                .username("honggildong1")
                .email("oasinfdoisa@asiodbf.asofnd")
                .createdAt(LocalDateTime.now())
                .build();

        return CommonApi.ok(response);
    }

    @GetMapping("/me-test-error")
    public CommonApi<Object> meUserNotFound(){

        return CommonApi.error(UserErrorCode.USER_NOT_FOUND, "홍길동이라는 사용자가 없습니다.");
    }
    @GetMapping("/me-test-error-2")
    public CommonApi< AccountMeResponse> meNumberFormatException(){
        var response = AccountMeResponse.builder()
                .username("honggildong1")
                .email("oasinfdoisa@asiodbf.asofnd")
                .createdAt(LocalDateTime.now())
                .build();
        try {
            var tempStr = "아아아아아아암ㄴ래ㅑㄴㄹㄴㅁ애ㅑ러매랴야";
            var tempAge = Integer.parseInt(tempStr);
        }catch (Exception e) {
            throw new CommonApiException(ErrorCode.INTERNAL_SERVER_ERROR, e, "이거는 그냥 NumberFormatException 테스트");
        }
        return CommonApi.ok(response);
    }
}
