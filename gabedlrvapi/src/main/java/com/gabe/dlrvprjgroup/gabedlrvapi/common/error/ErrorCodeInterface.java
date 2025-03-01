package com.gabe.dlrvprjgroup.gabedlrvapi.common.error;

public interface ErrorCodeInterface {

    Integer getHttpStatusCode();
    Integer getInternalErrorCode();
    String getDescription();
}
