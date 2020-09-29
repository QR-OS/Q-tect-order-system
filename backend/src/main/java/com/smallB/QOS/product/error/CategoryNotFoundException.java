package com.smallB.QOS.product.error;

import com.smallB.QOS.global.error.ErrorCode;
import com.smallB.QOS.global.error.Exception.InvalidValueException;

public class CategoryNotFoundException extends InvalidValueException {
    public CategoryNotFoundException(String store_id){
        super(store_id , ErrorCode.CATEGORY_NOT_FOUND);
    }
}
