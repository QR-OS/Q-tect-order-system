package com.smallB.QOS.user.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class  UnauthorizedException extends InvalidValueException {
    public UnauthorizedException(){super("UnauthorizedException!", ErrorCode.LOGIN_INPUT_INVALID);}
}
