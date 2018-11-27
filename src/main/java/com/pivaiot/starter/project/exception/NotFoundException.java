package com.pivaiot.starter.project.exception;


import com.pivaiot.common.lang.exception.CommonException;

public class NotFoundException extends CommonException {
    public NotFoundException() {
        super(ErrorCodeEnum.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, ErrorCodeEnum.NOT_FOUND);
    }
}
