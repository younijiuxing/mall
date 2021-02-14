package com.yq.mall.member.controller.advice;

import com.yq.common.api.CommonResult;
import com.yq.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author leyangjie
 * @date 2021/2/14 17:14
 * @describe 全局异常处理
 */
@RestControllerAdvice
public class ControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception exception) {
        if (exception instanceof BusinessException) {
            return CommonResult.failed("请稍后再试:" + exception.getMessage());
        } else if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) exception;
            return CommonResult.failed("校验错误:" + e.getBindingResult().getFieldError().getDefaultMessage());
        }
        return CommonResult.failed(exception.getMessage());
    }
}
