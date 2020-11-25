package com.smallB.QOS.user.error.Exception;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(){
        super("Password is wrong");
    }
}
