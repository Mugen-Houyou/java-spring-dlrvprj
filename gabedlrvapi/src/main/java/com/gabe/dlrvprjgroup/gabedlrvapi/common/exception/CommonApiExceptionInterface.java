package com.gabe.dlrvprjgroup.gabedlrvapi.common.exception;

import com.gabe.dlrvprjgroup.gabedlrvapi.common.error.ErrorCodeInterface;

public interface CommonApiExceptionInterface {

    ErrorCodeInterface getErrorCodeInterface();

    String getErrorDescription();


}
