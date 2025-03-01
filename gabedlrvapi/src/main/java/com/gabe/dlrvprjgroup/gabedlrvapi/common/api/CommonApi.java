package com.gabe.dlrvprjgroup.gabedlrvapi.common.api;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCodeInterface;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonApi<T> {
    private CommonResult commonResult;

    @Valid
    private T body;

    public static <T> CommonApi<T> ok(T data){
        var commonApi = new CommonApi<T>();
        commonApi.commonResult = CommonResult.ok();
        commonApi.body = data;
        return commonApi; // 예시: String data 인풋 ==> CommonApi<String> commonApi가 리턴됨.
    }

    public static CommonApi<Object> error(CommonResult commonResult){
        var commonApi = new CommonApi<Object>();
        commonApi.commonResult = commonResult;
        return commonApi;
    }

    public static CommonApi<Object> error(ErrorCodeInterface errorCodeInterface){
        var commonApi = new CommonApi<Object>();
        commonApi.commonResult = CommonResult.error(errorCodeInterface);
        return commonApi;
    }

    public static CommonApi<Object> error(ErrorCodeInterface errorCodeInterface, Throwable tw){
        var commonApi = new CommonApi<Object>();
        commonApi.commonResult = CommonResult.error(errorCodeInterface, tw);
        return commonApi;
    }

    public static CommonApi<Object> error(ErrorCodeInterface errorCodeInterface, String errorMessage){
        var commonApi = new CommonApi<Object>();
        commonApi.commonResult = CommonResult.error(errorCodeInterface, errorMessage);
        return commonApi;
    }


}
