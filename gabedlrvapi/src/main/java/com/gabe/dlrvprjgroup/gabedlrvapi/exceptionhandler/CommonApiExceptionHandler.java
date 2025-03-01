package com.gabe.dlrvprjgroup.gabedlrvapi.exceptionhandler;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.api.CommonApi;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.exception.CommonApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) // ==> 가장 우선 나와야 하므로.
public class CommonApiExceptionHandler {

    @ExceptionHandler(value = CommonApiException.class)
    public ResponseEntity<CommonApi<Object>> apiException(
            CommonApiException commonApiException
    ){
        log.error("",commonApiException);
        var errorCode = commonApiException.getErrorCodeInterface();
        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(CommonApi.error(errorCode, commonApiException.getErrorDescription()));
    }

}
