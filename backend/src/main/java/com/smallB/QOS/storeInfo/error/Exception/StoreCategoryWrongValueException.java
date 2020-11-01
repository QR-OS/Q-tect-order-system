package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreCategoryWrongValueException extends InvalidValueException {
    public StoreCategoryWrongValueException() { super(ErrorCode.STORE_CATEGORY_INPUT_INVALID); }
}
