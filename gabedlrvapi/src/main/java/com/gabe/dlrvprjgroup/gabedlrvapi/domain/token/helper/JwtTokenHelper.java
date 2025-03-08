package com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.helper;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.TokenErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.ifs.TokenHelperIfs;
import com.gabe.dlrvprjgroup.gabedlrvapi.domain.token.model.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenPlusHour);
        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                    ZoneId.systemDefault()
                ).toInstant());
        var signedKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // Deprecated.
//        var jwtToken = Jwts.builder()
//                .signWith(signedKey, SignatureAlgorithm.HS256)
//                .setClaims(data)
//                .setExpiration(expiredAt)
//                .compact();
        var jwtToken = Jwts.builder()
                .claims(data)
                .expiration(expiredAt)
                .signWith(signedKey)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenPlusHour);
        var expiredAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant());
        var signedKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        // Deprecated.
//        var jwtToken = Jwts.builder()
//                .signWith(signedKey, SignatureAlgorithm.HS256)
//                .setClaims(data)
//                .setExpiration(expiredAt)
//                .compact();
        var jwtToken = Jwts.builder()
                .claims(data)
                .expiration(expiredAt)
                .signWith(signedKey)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validateTokenWithThrow(String token) {
        var signedKey = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        var parser = Jwts.parser()
                .verifyWith(signedKey)
                .build();

        try{
            var result = parser.parseSignedClaims(token);
            return new HashMap<String,Object>(result.getPayload());

        }catch (Exception e){
            if( e instanceof SignatureException){
                throw new CommonApiException(TokenErrorCode.TOKEN_NOT_VALID, e);
                // 유효하지 않은 토큰.
            }else if (e instanceof ExpiredJwtException){
                // 만료된 토큰.
                throw new CommonApiException(TokenErrorCode.TOKEN_EXPIRED, e);
            }else{
                // 이외의 경우.
                throw new CommonApiException(TokenErrorCode.TOKEN_GENERAL_EXCEPTION, e);
            }
        }
    }
}
