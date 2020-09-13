package com.smallB.QOS.storeInfo.error.Exception;


import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreNotExistedException extends InvalidValueException {
    public StoreNotExistedException() { super(ErrorCode.STORE_ID_NOT_EXIST); }
}
