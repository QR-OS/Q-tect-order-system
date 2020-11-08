package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class StoreCategoryParameterWrongValueException extends InvalidValueException {
    public StoreCategoryParameterWrongValueException() {super(ErrorCode.STORE_CATEGORY_PARAM_INVALID);}
}
