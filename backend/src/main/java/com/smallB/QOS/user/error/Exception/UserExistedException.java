package com.smallB.QOS.user.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class UserExistedException extends InvalidValueException {
    public UserExistedException(String user_id){
        super(user_id, ErrorCode.USER_ID_DUPLICATION);
    }
}
