package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreUpdateFailedException extends InvalidValueException {
    public StoreUpdateFailedException() {
        super("Store update failed!", ErrorCode.STORE_UPDATE_FAILED);
    }
}
