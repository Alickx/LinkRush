package com.alickx.linkrush.api.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LinkExpireTypeEnum {

    HOUR_1(1, 60 * 60L, "1小时"),

    HOUR_6(2, 60 * 60 * 6L, "6小时"),

    DAY_1(3, 60 * 60 * 24L, "1天"),

    DAY_7(4, 60 * 60 * 24 * 7L, "7天"),
    ;

    private final Integer code;

    private final Long expireTime;

    private final String desc;

    public static LinkExpireTypeEnum getByCode(Integer code) {
        for (LinkExpireTypeEnum value : LinkExpireTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
