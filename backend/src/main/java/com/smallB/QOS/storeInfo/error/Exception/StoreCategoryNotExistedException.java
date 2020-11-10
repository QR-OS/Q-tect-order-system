package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreCategoryNotExistedException extends InvalidValueException {
    public StoreCategoryNotExistedException() { super(ErrorCode.STORE_CATEGORY_NOT_EXIST); }
}
