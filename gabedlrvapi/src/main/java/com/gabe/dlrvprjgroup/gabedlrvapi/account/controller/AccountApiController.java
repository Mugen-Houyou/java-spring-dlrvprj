package com.gabe.dlrvprjgroup.gabedlrvapi.account.controller;

import com.gabe.dlrvprjgroup.gabedlrvapi.account.model.AccountMeResponse;
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
    public AccountMeResponse me(){
        return AccountMeResponse.builder()
                .username("honggildong1")
                .email("oasinfdoisa@asiodbf.asofnd")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
