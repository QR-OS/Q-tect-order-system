package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreCategoryNotDeletedException extends InvalidValueException {
    public StoreCategoryNotDeletedException() { super(ErrorCode.STORE_CATEGORY_DELETE_FAILED); }
}
