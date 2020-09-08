package com.smallB.QOS.user.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class UserUpdateFailedException extends InvalidValueException {
    public UserUpdateFailedException(){
        super("User update failed!", ErrorCode.USER_UPDATE_FAILED);
    }
}
