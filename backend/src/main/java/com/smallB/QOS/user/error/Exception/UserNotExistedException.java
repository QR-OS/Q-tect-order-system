package com.smallB.QOS.user.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class UserNotExistedException extends InvalidValueException {
    public UserNotExistedException(String user_id){
        super(user_id, ErrorCode.USER_ID_NOT_EXIST);
    }
}
