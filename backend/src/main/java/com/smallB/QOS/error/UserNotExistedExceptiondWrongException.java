package com.smallB.QOS.error;

public class UserNotExistedExceptiondWrongException extends RuntimeException {
    public UserNotExistedExceptiondWrongException(String user_id){
        super("User_id is not registered:"+user_id);
    }
}
