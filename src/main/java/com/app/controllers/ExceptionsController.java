package com.app.controllers;

import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsController {

    @ExceptionHandler({MyException.class})
    public String myExceptionHandler(MyException ex) {
        if (ex.getExceptionInfo().getExceptionCode().equals(ExceptionCode.TOKEN)) {
            return "voters/wrongToken";
        }
        else {
            return "error";
        }
    }
}
