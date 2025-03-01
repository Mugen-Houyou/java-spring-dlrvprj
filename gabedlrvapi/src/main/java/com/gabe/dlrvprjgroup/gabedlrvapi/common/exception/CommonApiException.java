package com.gabe.dlrvprjgroup.gabedlrvapi.common.exception;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCodeInterface;
import lombok.Getter;

@Getter
public class CommonApiException extends RuntimeException implements  CommonApiExceptionInterface{

    private final ErrorCodeInterface errorCodeInterface;
    private final String errorDescription;

    public CommonApiException(
            ErrorCodeInterface errorCodeInterface
    ){
        super(errorCodeInterface.getDescription()); // 부모, 즉 RuntimeException에 메시지를 올려주기.
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorCodeInterface.getDescription();
    }

    public CommonApiException(
            ErrorCodeInterface errorCodeInterface,
            String errorDescription
    ){
        super(errorCodeInterface.getDescription()); // 부모, 즉 RuntimeException에 메시지를 올려주기.
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorDescription;
    }

    public CommonApiException(
            ErrorCodeInterface errorCodeInterface,
            Throwable tw
    ){
        super(tw); // 부모, 즉 RuntimeException에 Throwable 객체를 올려주기.
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorCodeInterface.getDescription();
    }

    public CommonApiException(
            ErrorCodeInterface errorCodeInterface,
            Throwable tw,
            String errorDescription
    ){
        super(tw); // 부모, 즉 RuntimeException에 Throwable 객체를 올려주기.
        this.errorCodeInterface = errorCodeInterface;
        this.errorDescription = errorDescription;
    }

}
