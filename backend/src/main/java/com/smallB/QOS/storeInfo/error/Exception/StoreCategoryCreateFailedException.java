package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreCategoryCreateFailedException extends InvalidValueException {
    public StoreCategoryCreateFailedException() { super(ErrorCode.STORE_CATEGORY_CREATE_FAILED); }
}
