package com.smallB.QOS.error;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class SessionErrorAdvice {

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(PasswordWrongException.class)
    public String handlePasswordWrong(){ return "{Password is wrong!}"; }

    @ResponseBody
    @ResponseStatus
    @ExceptionHandler(UserNotExistedExceptiondWrongException.class)
    public String handleUserIdWrong(){
        return "{User not found!}";
    }
}
