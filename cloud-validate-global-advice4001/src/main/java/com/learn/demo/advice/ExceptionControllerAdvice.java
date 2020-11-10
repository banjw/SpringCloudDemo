package com.learn.demo.advice;

import com.learn.demo.entities.CommonResult;
import com.learn.demo.exception.ControllerAPIException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * RestControllerAdvice
 *
 * @author banjiawei
 * @date 2020/11/10
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult<Object> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 从异常对象中拿到ObjectError对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        // 然后提取错误提示信息进行返回
        return new CommonResult<>(-1, "参数校验失败", objectError.getDefaultMessage());
    }
    @ExceptionHandler(ControllerAPIException.class)
    public CommonResult<Object> APIExceptionHandler(ControllerAPIException e){
        // 注意哦，这里返回类型是自定义响应体
        return new CommonResult<>(-1, "响应失败", e.getMsg());
    }
}
