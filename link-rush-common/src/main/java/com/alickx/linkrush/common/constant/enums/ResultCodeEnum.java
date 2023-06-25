package com.alickx.linkrush.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),

    FAIL(500,"失败"),

    ;

    private final Integer code;

    private final String msg;

}
