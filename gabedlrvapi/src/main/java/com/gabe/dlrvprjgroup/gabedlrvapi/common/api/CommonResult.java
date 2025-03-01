package com.gabe.dlrvprjgroup.gabedlrvapi.common.api;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCode;
import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCodeInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommonResult {

    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;
    public static CommonResult ok(){
        return CommonResult.builder()
                .resultCode(ErrorCode.OK.getHttpStatusCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("요청한 작업에 성공하였습니다.")
                .build();
    }

    public static CommonResult error(ErrorCodeInterface errorCodeInterface){
        return CommonResult.builder()
                .resultCode(errorCodeInterface.getHttpStatusCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription("요청한 작업에 실패하였습니다.")
                .build();
    }

    public static CommonResult error(ErrorCodeInterface errorCodeInterface, Throwable tw){
        return CommonResult.builder()
                .resultCode(errorCodeInterface.getHttpStatusCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription(tw.getLocalizedMessage())
                .build();
    }

    public static CommonResult error(ErrorCodeInterface errorCodeInterface, String errorMessage){
        return CommonResult.builder()
                .resultCode(errorCodeInterface.getHttpStatusCode())
                .resultMessage(errorCodeInterface.getDescription())
                .resultDescription(errorMessage)
                .build();
    }


}
