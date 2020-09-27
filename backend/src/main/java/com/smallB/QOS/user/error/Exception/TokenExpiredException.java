package com.smallB.QOS.user.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class TokenExpiredException extends InvalidValueException {
    public TokenExpiredException(){ super("JWT is expired",ErrorCode.TOKEN_EXPIRED); }
}
