package com.smallB.QOS.error;

public class PasswordWrongException extends RuntimeException{
    public PasswordWrongException(){
        super("Password is wrong");
    }
}
