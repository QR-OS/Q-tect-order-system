package com.smallB.QOS.productCategory.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class ProductCategoryDuplicatedException extends InvalidValueException {
    public ProductCategoryDuplicatedException(){
        super(ErrorCode.CATEGORY_DUPLICATED);
    }
}
