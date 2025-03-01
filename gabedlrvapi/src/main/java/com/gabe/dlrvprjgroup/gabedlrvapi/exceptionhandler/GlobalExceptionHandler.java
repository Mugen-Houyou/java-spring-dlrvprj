package com.gabe.dlrvprjgroup.gabedlrvapi.exceptionhandler;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.api.CommonApi;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 전역 예외 핸들러 ==> 최후의 보루
@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE) // 가장 마지막이어야 하므로 최댓값을 준다.
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonApi<Object>> exception(
            Exception exception
    ){
        log.error("",exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonApi.error(ErrorCode.INTERNAL_SERVER_ERROR));

    }
}
