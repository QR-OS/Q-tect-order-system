package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class UnauthorizedCategoryException extends InvalidValueException {
    public UnauthorizedCategoryException() {super("UnauthorizedCategoryException!", ErrorCode.CATEGORY_INPUT_INVALID);}
}
