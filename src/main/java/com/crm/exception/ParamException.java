package com.crm.exception;

import com.crm.vo.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by IMGXD on 2017/8/10.
 */

@ControllerAdvice

public class ParamException extends RuntimeException {
    private Integer errorCode;
    public ParamException() {
    }

    public ParamException(String message) {
        super(message);
        this.errorCode = 0;
    }

    public ParamException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }


    @ExceptionHandler({ParamException.class})
    public ServerResponse handlerParamException(ParamException e){
    return new ServerResponse();
}
}
