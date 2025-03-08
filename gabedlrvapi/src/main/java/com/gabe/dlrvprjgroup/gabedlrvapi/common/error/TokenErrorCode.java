package com.gabe.dlrvprjgroup.gabedlrvapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 내부코드 정의:
 *  - JWT 관련 오류: 2000번대 사용
 */
@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeInterface {

    TOKEN_NOT_VALID( 400, 2000, "유효하지 않은 토큰"),
    TOKEN_EXPIRED(400, 2001, "만료된 토큰"),
    TOKEN_GENERAL_EXCEPTION(400, 2002, "토큰 관련한 일반적인 오류"),
    TOKEN_MISSING( 400, 2003, "유저로부터 받은 정보에 토큰이 없음")
    ;

    private final Integer httpStatusCode;

    private final Integer internalErrorCode;

    private final String description;
}
