package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreRegisterFailedException extends InvalidValueException {
    public StoreRegisterFailedException()  {
        super("Store register failed!", ErrorCode.STORE_REGISTER_FAILED);
    }
}
