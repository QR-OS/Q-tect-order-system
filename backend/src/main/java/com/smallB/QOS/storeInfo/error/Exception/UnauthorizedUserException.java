package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class UnauthorizedUserException extends InvalidValueException {
    public UnauthorizedUserException(String user_id) {
        super(user_id, ErrorCode.UNAUTHORIZED_USER);
    }
}
