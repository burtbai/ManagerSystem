package com.bai.core.exception;

import com.bai.domain.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author burtbai
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        e.printStackTrace();
        return Result.builder().code(500).message(e.getMessage()).build();
    }
}
