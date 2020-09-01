package com.smallB.QOS.user.error;

public class UserExistedException extends RuntimeException{
    public UserExistedException(String user_id){
        super("User_id is already registered:"+user_id);
    }
}
