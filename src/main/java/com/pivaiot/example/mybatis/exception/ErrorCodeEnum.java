package com.pivaiot.example.mybatis.exception;


import com.pivaiot.common.lang.exception.ErrorCode;

public enum ErrorCodeEnum implements ErrorCode {
    UNKNOWN(100001),
    NOT_FOUND(100404);

    private Integer id;

    ErrorCodeEnum(Integer id) {
        this.id = id;
    }

    @Override
    public String getErrorCode() {
        return toString();
    }

    public Integer getId() {
        return id;
    }
}
