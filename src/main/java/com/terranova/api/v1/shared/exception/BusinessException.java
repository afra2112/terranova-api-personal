package com.terranova.api.v1.shared.exception;

import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCodeEnum errorCodeEnum;

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum, String customMessage){
        super(customMessage);
        this.errorCodeEnum = errorCodeEnum;
    }
}
