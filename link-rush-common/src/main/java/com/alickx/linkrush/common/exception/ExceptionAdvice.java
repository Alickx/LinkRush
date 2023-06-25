package com.alickx.linkrush.common.exception;

import com.alickx.linkrush.common.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(ValidationException.class)
    public R<String> handleValidationException(ValidationException e, HttpServletRequest request) {
        log.error("请求地址: {}, 参数校验异常 ex={}", request.getRequestURI(), e.getMessage());
        return R.fail(e.getLocalizedMessage());
    }

    @ExceptionHandler(BindException.class)
    public R<String> handleBodyValidException(BindException e, HttpServletRequest request) {
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getErrorCount() > 0 ? bindingResult.getAllErrors().get(0).getDefaultMessage()
                : "未获取到错误信息!";
        log.error("请求地址: {}, 参数绑定异常 ex={}", request.getRequestURI(), errorMsg);
        return R.fail(errorMsg);
    }

    /**
     * 自定义业务异常捕获 业务异常响应码推荐使用200 用 result 结构中的code做为业务错误码标识
     * @param e the e
     * @return R
     */
    @ExceptionHandler(BusinessException.class)
    public R<String> handleBallCatException(BusinessException e, HttpServletRequest request) {
        log.error("请求地址: {}, 业务异常信息 ex={}", request.getRequestURI(), e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }


}
