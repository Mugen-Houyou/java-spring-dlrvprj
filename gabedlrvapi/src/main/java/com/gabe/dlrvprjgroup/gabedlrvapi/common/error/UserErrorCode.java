package com.gabe.dlrvprjgroup.gabedlrvapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 내부코드 정의:
 *  - User 관련 오류: 1000번대 사용
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInterface {

    USER_NOT_FOUND( 400, 1404, "해당하는 유저가 없음"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 내부 오류"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point 오류" )
    ;

    private final Integer httpStatusCode;

    private final Integer internalErrorCode;

    private final String description;
}
