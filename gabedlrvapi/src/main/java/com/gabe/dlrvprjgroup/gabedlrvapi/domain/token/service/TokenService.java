package com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.service;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.ifs.TokenHelperIfs;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId ){
        var data = new HashMap<String,Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId){
        var data = new HashMap<String,Object>();
        data.put("userId", userId);
        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validateToken(String token){
        var map = tokenHelperIfs.validateTokenWithThrow(token);
        var userId = map.get("userId");
        Objects.requireNonNull(userId, () -> {
            throw new CommonApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }


}
