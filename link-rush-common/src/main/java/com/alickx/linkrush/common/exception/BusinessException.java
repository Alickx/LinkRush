package com.alickx.linkrush.common.exception;


import com.alickx.linkrush.common.constant.enums.ResultCodeEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final Integer code;

    private final String message;

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message) {
        this.code = ResultCodeEnum.FAIL.getCode();
        this.message = message;
    }


}
