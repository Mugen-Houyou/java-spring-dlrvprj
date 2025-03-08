package com.gabe.dlrvprjgroup.gabedlrvapi.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeInterface {

    OK( 200, 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 내부 오류"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null point 오류" ),
    USER_NOT_VALID(400,1401,"유효하지 않은 유저" );

    private final Integer httpStatusCode;

    private final Integer internalErrorCode;

    private final String description;


}
