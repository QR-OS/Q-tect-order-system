package com.smallB.QOS.productCategory.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class ProductCategoryNotFoundException extends InvalidValueException {
    public ProductCategoryNotFoundException(){
        super(ErrorCode.CATEGORY_NOT_FOUND);
    }
}
