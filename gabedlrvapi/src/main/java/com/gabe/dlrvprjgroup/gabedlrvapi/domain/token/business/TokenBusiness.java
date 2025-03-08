package com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.business;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.annotation.Business;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.controller.model.TokenResponse;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.converter.TokenConverter;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.service.TokenService;
import com.gabe.dlrvprjgroup.gabedlrvdb.BaseEntity;
import com.gabe.dlrvprjgroup.gabedlrvdb.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;

    private final TokenConverter tokenConverter;

    /**
     * 토큰 발급하기
     * 1. userEntity -> userId를 추출
     * 2. Access token 및 refresh token 발행
     * 3. Converter를 통해 TokenResponse로 -> 유저에게 답신.
     *
     * @param userEntity
     * @return tokenResponse
     */
    public TokenResponse issueToken(UserEntity userEntity){

        return Optional.ofNullable(userEntity)
                // .map(ue -> {
                //        return ue.getId();
                // })
                .map(BaseEntity::getId)
                .map(ui -> {
                    return  tokenConverter.toResponse(tokenService.issueAccessToken(ui),tokenService.issueRefreshToken(ui));
                })
                .orElseThrow(() ->
                        new CommonApiException(ErrorCode.NULL_POINT)
                );
    }

    public Long validateAccessToken(
            String accessToken
    ){
        return tokenService.validateToken(accessToken);
    }

}
