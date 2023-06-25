package com.alickx.linkrush.common.domain;

import com.alickx.linkrush.common.constant.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class R<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> R<T> ok() {
        R<T> r = new R<>();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        return r;
    }

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMsg(ResultCodeEnum.SUCCESS.getMsg());
        r.setData(data);
        return r;
    }

    public static <T> R<T> fail() {
        R<T> r = new R<>();
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMsg(ResultCodeEnum.FAIL.getMsg());
        return r;
    }

    public static <T> R<T> fail(String msg) {
        R<T> r = new R<>();
        r.setCode(ResultCodeEnum.FAIL.getCode());
        r.setMsg(msg);
        return r;
    }

    public static <T> R<T> fail(Integer code, String msg) {
        R<T> r = new R<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }


}
