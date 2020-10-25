package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreAlreadyExistException extends InvalidValueException {
    public StoreAlreadyExistException() { super(ErrorCode.STORE_ALREADY_EXIST); }
}
