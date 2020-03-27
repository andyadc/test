package com.bill99.ayf.auth.exception;

import com.bill99.ayf.auth.web.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * andy.an
 * 2020/3/27
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = {PermissionException.class})
    public @ResponseBody
    Response permissionHandler() {
        return new Response("500", "No Permission");
    }
}
