package com.terranova.api.v1.shared.exception;

import com.terranova.api.v1.shared.enums.ErrorCodeEnum;
import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        ErrorCodeEnum code,
        String message,
        int status,
        String path,
        LocalDateTime timestamp,
        List<FieldApiError> errors
) {
    public ApiError(ErrorCodeEnum code, String message, int status, String path){
        this(code, message, status, path, LocalDateTime.now(), null);
    }
}