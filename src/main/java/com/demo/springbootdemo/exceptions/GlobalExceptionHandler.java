package com.demo.springbootdemo.exceptions;

import com.demo.springbootdemo.vo.Result;
import com.demo.springbootdemo.vo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * 全局异常处理
 *
 * @author guankai
 * @date 2020/11/4
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求参数异常
     *
     * @author guankai
     * @date 2020/11/4
     * @param exception 异常
     * @param resultCode 返回编码
     * @return 返回前台结果
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Result methodArgumentNotValidExceptionHandler(Exception exception,ResultCode resultCode) {
        LOGGER.error("GlobalExceptionHandler 请求参数异常：", exception);
        return Result.failure(resultCode);
    }

    /**
     * 404异常
     *
     * @author guankai
     * @date 2020/11/4
     * @param exception 异常
     * @param resultCode 返回编码
     * @return
     */
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public Result noHandlerExceptionHandler(Exception exception,ResultCode resultCode) {
        LOGGER.error("GlobalExceptionHandler NoHandlerFoundException异常：", exception);
        return Result.failure(resultCode);
    }

    /**
     * 其他异常处理
     *
     * @author guankai
     * @date 2020/11/4
     * @param exception 异常
     * @param resultCode 返回编码
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    public Result exceptionHandler(Exception exception,ResultCode resultCode) {
        LOGGER.error("GlobalExceptionHandler 异常：", exception);
        return Result.failure(resultCode);
    }
}
