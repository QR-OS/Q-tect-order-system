package com.smallB.QOS.user.error;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(){
        super("Password is wrong");
    }
}
