package com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.converter;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.annotation.Converter;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.controller.model.TokenResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Converter
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDto accessToken,
            TokenDto refreshToken
    ){
        // accessToken이나 refreshToken이 없으면 바로 예외로.
        Objects.requireNonNull(accessToken,()->{
            throw new CommonApiException(ErrorCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken,()->{
            throw new CommonApiException(ErrorCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }

}
