package com.smallB.QOS.storeInfo.error.Exception;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class CategoryNotExistedException extends InvalidValueException {
    public CategoryNotExistedException() { super(ErrorCode.CATEGORY_NOT_EXIST); }
}
